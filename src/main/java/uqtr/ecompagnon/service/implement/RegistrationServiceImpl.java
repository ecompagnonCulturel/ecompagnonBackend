package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uqtr.ecompagnon.config.EmailValidator;
import uqtr.ecompagnon.dto.*;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.service.RegistrationService;
import uqtr.ecompagnon.util.exception.AppRequestException;
import org.springframework.core.env.Environment;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
@Setter
@Getter
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserServiceImpl appUserServiceImpl;
    private final CpMailServiceImpl cp_mailService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenServiceImpl confirmationTokenServiceImpl;
    private final ConfirmationPwdTokenServiveImpl confirmationPwdTokenServiveImpl;
     private final UtilServiceImpl utilServiceImpl;
    private final TemplateEngine templateEngine;
    private final FcmPushServiceImpl fcmPushService;
    private final AccountServiceImpl accountService;
    private final Environment env;
    //private final String URl="https://ecompagnonbackend.herokuapp.com";
   //  String URl=env.getProperty("backend");

    @Transactional
    @Override
    public String register(RegistrationRequestDTO request) {
        String email=request.getMailUsers();
        String cp=request.getCPUsers();
        email=email.toLowerCase();
        cp=cp.toUpperCase();
        String link=null;


        boolean isValidEmail = emailValidator
                .test(email);

        if (!isValidEmail) {
            throw new IllegalStateException("Courriel is not valid");
        }

        if (!cp_mailService.existsCpMailByMailAndCp(email, cp)) {
            throw new IllegalStateException("L'associaition entre ce code permanent et ce courriel n'existe pas encore dans le système.Veuillez contacter les auteurs pour l'ajout");
        }

        String token = appUserServiceImpl.signUpUser(
                new AppUser(
                        request.getLastname(),
                        request.getFirstname(),
                        email,
                        cp,
                        request.getPasswordUsers(),
                        AppUserRole.ETUDIANT,
                        request.getFcmToken()

                )
        );
        //String link="http://localhost:8091/api/registration/confirm?token="+token;

        link = env.getProperty("backend")+"/api/registration/confirm?token=" + token+"&fcmToken="+request.getFcmToken();
        String contenu = "Merci d'avoir créé votre compte. Veuillez cliquer sur ce lien pour l'activer:";
        String entete = "Confirmer votre courriel";
        String textLink = "Activer maintenant";
        String linkSite = env.getProperty("linkSite");
        String textSite = "www.uqtr.ca/mediateursculturels";
        String subject = "Confirmer votre inscription sur eCompagnon Culturel";
        Context context = new Context();
        context.setVariable("link", link);
        context.setVariable("contenu", contenu);
        context.setVariable("entete", entete);
        context.setVariable("textLink", textLink);
        context.setVariable("textSite", textSite);
        context.setVariable("linkSite", linkSite);
        context.setVariable("subject", subject);
        context.setVariable("name", request.getFirstname());
        String process = templateEngine.process("courriel", context);
        // String link=" http://localhost:8100/login";
        //utilServiceImpl.sendMail(request.getMailUsers(), buildEmail(request.getFirstname(),link,entete,contenu,textLink),subject);
       try  {
           utilServiceImpl.sendMailGmail(email, process, subject);
        }
       catch (Exception e)
       {
           throw new AppRequestException("Impossible d\'envoyer ce courriel");

       }

        return token;
    }




    @Transactional
    @Override
    public String confirmToken(String token,String fcmToken) {
        ConfirmationToken confirmationToken = confirmationTokenServiceImpl
                .findByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Token not found"
                        ));
        if (confirmationToken.getConfirmeAt() != null) {
            throw new IllegalStateException("Courriel déjà validé");
        }

        LocalDateTime expiredAT = confirmationToken.getExpiresAt();

        if (expiredAT.isBefore((LocalDateTime.now()))) {
            throw new IllegalStateException("Token expiré");
        }
        FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
        System.out.println(fcmToken);
        fcmRequestDTO.setToken(fcmToken);
        fcmRequestDTO.setTitle("Bienvenue dans eCompagnon culturel");
        fcmRequestDTO.setMessage("Vous avez un premier questionnaire à remplir !");
        fcmRequestDTO.setTopic(null);
        fcmRequestDTO.setUserId(Math.toIntExact(confirmationToken.getAppUser().getIdUsers()));
        confirmationToken.setConfirmeAt(LocalDateTime.now());
        fcmPushService.sendMessageWithDataToToken(fcmRequestDTO);
        appUserServiceImpl.enableAppUser
                (confirmationToken.getAppUser().getMailUsers());

        return token;

    }

    @Transactional
    @Override
    public AppUser confirmPwdTokenAndInitPwd(String token,String password) {
        ConfirmationPwdToken confirmationPwdToken = confirmationPwdTokenServiveImpl
                .findByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Token not found"
                        ));
        AppUser appUser = confirmationPwdToken.getAppUser();

        if (confirmationPwdToken.getConfirmeAt() != null) {
            throw new IllegalStateException("mot de passe  déjà modifiée");
        }

        LocalDateTime expiredAT = confirmationPwdToken.getExpiresAt();

        if (expiredAT.isBefore((LocalDateTime.now()))) {
            throw new IllegalStateException("Lien expiré expiré");
        }

        confirmationPwdToken.setConfirmeAt(LocalDateTime.now());
        confirmationPwdTokenServiveImpl.saveConfirmationToken(confirmationPwdToken);

        appUserServiceImpl.initPwdAppUser(appUser, password);

        return appUser;

    }

    @Override
    public AppUser confirmPwdTokenAndInitPwd(InitPwdDTO initPwdDTO) {
        ConfirmationPwdToken confirmationPwdToken = confirmationPwdTokenServiveImpl
                .findByToken(initPwdDTO.getToken())
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Token not found"
                        ));
        AppUser appUser = confirmationPwdToken.getAppUser();

        if (confirmationPwdToken.getConfirmeAt() != null) {
            throw new IllegalStateException("mot de passe  déjà modifiée");
        }

        LocalDateTime expiredAT = confirmationPwdToken.getExpiresAt();

        if (expiredAT.isBefore((LocalDateTime.now()))) {
            throw new IllegalStateException("Lien expiré expiré");
        }

        confirmationPwdToken.setConfirmeAt(LocalDateTime.now());
        confirmationPwdTokenServiveImpl.saveConfirmationToken(confirmationPwdToken);

        appUserServiceImpl.initPwdAppUser(appUser, initPwdDTO.getPasswordUsers());

        return appUser;

    }

    @Override
    public String reSendConfirmation(String mail) {

        AppUser appUser = (AppUser) appUserServiceImpl.loadUserByUsername(mail);
        ConfirmationToken confirmationToken = confirmationTokenServiceImpl.findByAppUser(appUser)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Token not found"
                        ));
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationTokenServiceImpl.saveConfirmationToken(confirmationToken);
        String token = confirmationToken.getToken();

        String link = env.getProperty("backend")+"/api/confirm?token=" + token;
        String linkSite = env.getProperty("linkSite");
        //String link="https://ecompagnon.com/confirm?token="+token;
        String contenu = "Merci pour la création de votre  compte sur ECompagnon Culturel. Veuillez cliquer sur ce lien pour l'activer:";
        String entete = "Confirmer votre courriel";
        String textLink = "Activer Maintenant";
        String textSite = "www.uqtr.ca/mediateursculturels";
        String subject = "Confirmer votre inscription sur eCompagnon Culturel";
        Context context = new Context();
        context.setVariable("link", link);
        context.setVariable("contenu", contenu);
        context.setVariable("entete", entete);
        context.setVariable("textLink", textLink);
        context.setVariable("textSite", textSite);
        context.setVariable("linkSite", linkSite);
        context.setVariable("subject", subject);
        context.setVariable("name", appUser.getFirstname());
        String process = templateEngine.process("courriel", context);
        // String link=" http://localhost:8100/login";
        //utilServiceImpl.sendMail(request.getMailUsers(), buildEmail(request.getFirstname(),link,entete,contenu,textLink),subject);
        utilServiceImpl.sendMailGmail(appUser.getMailUsers(), process, subject);

        return token;

    }

    @Override
    public AppUser updateAppUser(AppUserUpdateDTO appUserUpdateDTO) {
        boolean isValidEmail = emailValidator
                .test(appUserUpdateDTO
                        .getMailUsers());

        if (!isValidEmail) {
            throw new AppRequestException("Courriel is not valid");
        }

        if (!cp_mailService.existsCpMailByMailAndCp(appUserUpdateDTO.getMailUsers(), appUserUpdateDTO.getCPUsers())) {
            throw new AppRequestException("Code Permanent et Couriel ne correspondent pas");
        }

        AppUser appUserUpdate = appUserServiceImpl.updateUpUser(
                new AppUser(
                        appUserUpdateDTO.getIdUsers(),
                        appUserUpdateDTO.getLastname(),
                        appUserUpdateDTO.getFirstname(),
                        appUserUpdateDTO.getMailUsers(),
                        appUserUpdateDTO.getCPUsers(),
                        appUserUpdateDTO.getPasswordUsers(),
                        AppUserRole.ETUDIANT

                )
        );
        return appUserUpdate;

    }

    @Override
    public LoginResponseDTO updateAppUserFcmToken(FormFieldDTO formFieldDTO) {

        Optional<AppUser> appUser= appUserServiceImpl.getUserById(formFieldDTO.getId());
        appUser.get().setTokenNotific(formFieldDTO.getValue());
        AppUser appUserObjet =appUserServiceImpl.updateUpUser(appUser.get());

        LoginResponseDTO loginResponseDTO= new LoginResponseDTO(
                appUserObjet.getIdUsers(),
                appUserObjet.getLastname(),
                appUserObjet.getFirstname(),
                appUserObjet.getMailUsers(),
                appUserObjet.getCPUsers(),
                appUserObjet.getFormField(),
                appUserObjet.getTokenNotific()
        );


        return loginResponseDTO;

    }

    @Transactional
    @Override
    public String forgetPwd(UserCredentialDTO userCredentialDTO) {
        AppUser appUserNew = (AppUser) appUserServiceImpl.loadUserByUsername(userCredentialDTO.getEmail());
        //boolean userExists = appUserNew.

        if (appUserNew != null) {
            String token = appUserServiceImpl.forgetAppUserPwd(appUserNew);
            String requestBody = "{token:"+ token+",passwordUsers:"+userCredentialDTO.getPassword()+"}";

                //String link="http://localhost:8091/api/registration/confirmPwd?token="+token;
              //  HttpsURLConnection link = con;
                String link=env.getProperty("backend")+"/api/registration/confirmPwdTokenAndInitPwd?a="+token+"&b="+userCredentialDTO.getPassword();
                String contenu = "Veuillez cliquer sur ce lien pour confirmer votre nouveau mot de passe:";
                String entete = "Réinitialiser  votre mot de passe";
                String textLink = "Réinitialiser Maintenant";
                String subject = "Réinitialiser votre mot de passe sur eCompagnon Culturel";
                Context context = new Context();
                context.setVariable("link", link);
                context.setVariable("contenu", contenu);
                context.setVariable("entete", entete);
                context.setVariable("textLink", textLink);
                context.setVariable("subject", subject);
                context.setVariable("name", appUserNew.getFirstname());
                System.out.println(appUserNew.getFirstname());
                String process = templateEngine.process("courriel", context);
                // String link=" http://localhost:8100/login";
                //utilServiceImpl.sendMail(request.getMailUsers(), buildEmail(request.getFirstname(),link,entete,contenu,textLink),subject);
                utilServiceImpl.sendMailGmail(appUserNew.getMailUsers(), process, subject);
                  return token;

        } else {
            throw new AppRequestException("Ce compte n'existe pas");

        }


    }


}

