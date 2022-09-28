package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.AppUserUpdatePwdDTO;
import uqtr.ecompagnon.dto.UserCredentialDTO;
import uqtr.ecompagnon.dto.UserListDTO;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationPwdToken;
import uqtr.ecompagnon.model.ConfirmationToken;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.service.AppUserService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("appAuthenticator")
@AllArgsConstructor
@Transactional
public class AppUserServiceImpl implements UserDetailsService, AppUserService {
    private final static String USER_NOT_FOUND =
            "Utilisateur avec cette email %s non trouvé";
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenServiceImpl confirmationTokenServiceImpl;
    private final ConfirmationPwdTokenServiveImpl confirmationPwdTokenServive;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByMailUsers(email)
                .orElseThrow(() ->
                        new AppRequestException("Ce compte n'existe pas"));
    }

    public UserDetails loadUserByUserCP(String CP)
            throws UsernameNotFoundException {
        return appUserRepository.findByCPUsers(CP)
                .orElseThrow(() ->
                        new AppRequestException("Cet utilisateur n'existe pas"));
    }

    @Override
    public String signUpUser(AppUser appUser) {

        Optional<AppUser> appUserNew = appUserRepository.findByMailUsers(appUser.getMailUsers());
        boolean userExists = appUserNew.isPresent();
        String token = UUID.randomUUID().toString();

        if ((userExists) && (!appUserNew.equals(appUser))) {

            throw new AppRequestException("Ce courriel  est déjà utilisé");
        } else if ((userExists) && (appUserNew.equals(appUser)) && (appUser.getEnabled() == false)) {
            // String link="http://localhost:8091/api/registration/confirm?token="+token;

            // String link=" http://localhost:8100/login";
            //  utilServiceImpl.sendMail(appUser.getMailUsers(), registrationService.buildEmail(appUser.getFirstname(),link));
            throw new AppRequestException("Un courriel vous a été envoyé pour la confirmation de votre compte");

        }

        String encode = passwordEncoder.encode(appUser.getPassword());
        appUser.setPasswordUsers(encode);

        appUserRepository.save(appUser);


        //create Confirmation Token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenServiceImpl.saveConfirmationToken(
                confirmationToken
        );

        //Envoyer le token par mail

        return token;


    }

    @Override
    public AppUser updateUpUser(AppUser appUser) {
        Optional<AppUser> appUserNew = appUserRepository.findByMailUsers(appUser.getMailUsers());
        boolean userExists = appUserNew.isPresent();

        if (userExists) {

            //String encode = passwordEncoder.encode(appUser.getPassword());
            appUser.setPasswordUsers(appUserNew.get().getPassword());

            appUserRepository.save(appUser);
        } else  {
            // String link="http://localhost:8091/api/registration/confirm?token="+token;

            // String link=" http://localhost:8100/login";
            //  utilServiceImpl.sendMail(appUser.getMailUsers(), registrationService.buildEmail(appUser.getFirstname(),link));
            throw new AppRequestException("Ce compte n'existe pas");

        }

       return appUser;
    }

    @Override
    public AppUser updateUpUserPwd(AppUserUpdatePwdDTO appUserUpdatePwdDTO) {
        Optional<AppUser> appUserNew = appUserRepository.findByMailUsers(appUserUpdatePwdDTO.getMailUsers());
        boolean userExists = appUserNew.isPresent();

        if (userExists) {

              String newPwd = passwordEncoder.encode(appUserUpdatePwdDTO.getPasswordUsers());
            if (passwordEncoder.matches(appUserUpdatePwdDTO.getOldPasswordAUsers(), appUserNew.get().getPassword())){
                if (!passwordEncoder.matches(appUserUpdatePwdDTO.getPasswordUsers(), appUserNew.get().getPassword())) {
                    //String encode = passwordEncoder.encode(appUser.getPassword());
                    appUserNew.get().setPasswordUsers(newPwd);
                    appUserRepository.save(appUserNew.get());

                    return  appUserNew.get();

                }
                else
                {
                    throw new AppRequestException(
                            "Pas de différence entre l'ancien et le nouveau mot de passe"
                    );
                }

            }
            else {
                throw new AppRequestException("L'ancien mot de passe est incorrect");
            }

        } else  {

            throw new AppRequestException("Ce compte n'existe pas");

        }


    }

    @Override
    public AppUser initPwdAppUser(AppUser appUser,String password) {

            //String encode = passwordEncoder.encode(appUser.getPassword());
            appUser.setPasswordUsers(passwordEncoder.encode(password));

            appUserRepository.save(appUser);
            return appUser;


    }

    @Override
    public void initPwdAppUserNoToken(UserCredentialDTO userCredentialDTO) {
    AppUser appUser=appUserRepository.findByMailUsers(userCredentialDTO.getEmail())
        .orElseThrow(() ->
                new AppRequestException("Cet utilisateur n'existe pas"));
        //String encode = passwordEncoder.encode(appUser.getPassword());
        appUser.setPasswordUsers(passwordEncoder.encode(userCredentialDTO.getPassword()));

        appUserRepository.save(appUser);



    }


    @Override
    public void enableAppUser(String email) {
        AppUser appUser = appUserRepository.findByMailUsers(email)
                .orElseThrow(() ->
                        new IllegalStateException("Email Inexistant"));

        appUser.setEnabled(true);

    }

   public Optional<AppUser> getUserById(long id) {
       return appUserRepository.findById(id);

    }

    public String forgetAppUserPwd(AppUser appUser) {
        String token = UUID.randomUUID().toString();

            //create Confirmation Token
            ConfirmationPwdToken confirmationPwdToken = new ConfirmationPwdToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    appUser
            );

            confirmationPwdTokenServive.saveConfirmationToken(
                    confirmationPwdToken
            );


            return token;

            }
    @Override
    public AppUser verifyFinishUser(String cpUser) {
        String yearNow= String.valueOf(LocalDateTime.now().getYear());
        return appUserRepository.verifyFinishuser(cpUser,yearNow);
    }

    @Override
    public List<Object> findAllUsers(){
        return appUserRepository.findAllUsers();
    }

}
