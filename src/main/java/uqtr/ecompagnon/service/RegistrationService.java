package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.*;
import uqtr.ecompagnon.model.AppUser;

public interface RegistrationService {
    String register(RegistrationRequestDTO request);
    String confirmToken(String token,String fcmToken);
    AppUser confirmPwdTokenAndInitPwd(InitPwdDTO initPwdDTO);
    AppUser confirmPwdTokenAndInitPwd(String a, String b);
    String reSendConfirmation(String mail);
    AppUser updateAppUser(AppUserUpdateDTO appUserUpdateDTO);
    String forgetPwd(UserCredentialDTO userCredentialDTO);
    LoginResponseDTO updateAppUserFcmToken(FormFieldDTO formFieldDTO);


  
}
