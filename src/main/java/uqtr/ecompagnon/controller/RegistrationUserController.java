package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.*;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.service.implement.AppUserServiceImpl;
import uqtr.ecompagnon.service.implement.RegistrationServiceImpl;
import uqtr.ecompagnon.util.exception.AppRequestException;

@Controller
@RequestMapping("api/registration")
@AllArgsConstructor
public class RegistrationUserController {
private final RegistrationServiceImpl registrationServiceImpl;
private final AppUserServiceImpl appUserService;



    @PostMapping(value ="/add")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequestDTO request)
    {
        String token= registrationServiceImpl.register(request);
        RegistrationResponseDTO registrationResponseDTO =new RegistrationResponseDTO(token);
        return new ResponseEntity(registrationResponseDTO, HttpStatus.OK);

    }

    @PostMapping(value ="/forgetPwd")
    public ResponseEntity<Object> forgetPwd(@RequestBody UserCredentialDTO userCredentialDTO)
    {
        try {
            String token = registrationServiceImpl.forgetPwd(userCredentialDTO);
            RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO(token);
            return new ResponseEntity(registrationResponseDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping(value ="/resendMail")
    public ResponseEntity<Object> resendMail(@RequestBody String mail)
    {
        String token= registrationServiceImpl.reSendConfirmation(mail);
        RegistrationResponseDTO registrationResponseDTO =new RegistrationResponseDTO(token);
        return new ResponseEntity(registrationResponseDTO, HttpStatus.OK);

    }
    @GetMapping(path ="/confirm")
    public String confirm(@RequestParam(value="token") String token,@RequestParam(value="fcmToken") String fcmToken)
    {
        String result="";
        try {
            String toke= registrationServiceImpl.confirmToken(token,fcmToken);
            result= "confirmAccount";
        }
        catch (Exception e)
        {
            if(e.getMessage()=="Courriel déjà validé")
            {
                result= "courrielValide";
            }
            else if(e.getMessage()=="Token expiré")
            {
                result= "lienExpire";
            }

        }
        return result;

    }


    @PostMapping(value ="/updateFcmToken")
    public ResponseEntity<Object> updateAppUserFcmToken(@RequestBody  FormFieldDTO formFieldDTO)
    {
        System.out.println("moi");
        LoginResponseDTO loginResponseDTO= registrationServiceImpl.updateAppUserFcmToken(formFieldDTO);
        return new ResponseEntity(loginResponseDTO, HttpStatus.OK);

    }


    @GetMapping(path ="/confirmPwdTokenAndInitPwd")
    public String confirmPwdTokenAndInitPwd(@RequestParam(name="a") String token,@RequestParam(name="b") String password)
    {
        String result="";
        try {
            AppUser appUser = registrationServiceImpl.confirmPwdTokenAndInitPwd(token,password);
            return  result= "initPassword";
        }
        catch (Exception e) {

                // TODO: handle exception
                if(e.getMessage()=="Ce compte n'existe pas")
                {
                    result= "accountNotExist";
                }
                else if(e.getMessage()=="Token not found")
                {
                    result= "urlNotOk";
                }
                else if(e.getMessage()=="mot de passe  déjà modifiée")
                {
                    result= "initedPassword";
                }
                else if(e.getMessage()=="Lien expiré expiré")
                {
                    result= "lienExpire";
                }
                return result;

        }
    }

    @GetMapping(path ="/confirmPwd")
    public String confirmPwd(@RequestBody InitPwdDTO initPwdDTO)
    {
        String result="";
        try {
            AppUser appUser = registrationServiceImpl.confirmPwdTokenAndInitPwd(initPwdDTO);
            return  result= "iniPassword";
        }
        catch (Exception e) {
            // TODO: handle exception
            if(e.getMessage()=="Ce compte n'existe pas")
            {
                result= "accountNotExist";
            }
            else if(e.getMessage()=="Token not found")
            {
                result= "urlNotOk";
            }
            else if(e.getMessage()=="mot de passe  déjà modifiée")
            {
                result= "initedPassword";
            }
            else if(e.getMessage()=="Lien expiré expiré")
            {
                result= "lienExpire";
            }
            return result;
        }
    }


   /* @GetMapping(path ="/initPwd")
    public ResponseEntity<Object> initPwd(@RequestParam UserCredentialDTO userCredentialDTO)
    {
        try {
            AppUser appUser = registrationServiceImpl.initPwd(userCredentialDTO);
            return new ResponseEntity(appUser, HttpStatus.OK);
        }
        catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }*/


    @PostMapping("/update")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> updateUser(@RequestBody AppUserUpdateDTO appUserUpdateDTO) {
        try {

            registrationServiceImpl.updateAppUser(appUserUpdateDTO);

            return new ResponseEntity("Utilisateur enregistré avec succès", HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Favoris.out.println(Favoris.toString());
            return new ResponseEntity("Erreur d'enregistrement du Favoris" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PostMapping("/updatePwd")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> updatePwd(@RequestBody AppUserUpdatePwdDTO appUserUpdatePwdDTO) {
        try {

            appUserService.updateUpUserPwd(appUserUpdatePwdDTO);

            return new ResponseEntity("mot enregistré avec succès" , HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
           // throw new AppRequestException("ACCOUNT_DISABLED");
            // Favoris.out.println(Favoris.toString());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/initPwdNoToken")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> initPwdNoToken(@RequestBody UserCredentialDTO userCredentialDTO) {
        try {

            registrationServiceImpl.forgetPwd(userCredentialDTO);

            return new ResponseEntity("mot enregistré avec succès" , HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // throw new AppRequestException("ACCOUNT_DISABLED");
            // Favoris.out.println(Favoris.toString());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
