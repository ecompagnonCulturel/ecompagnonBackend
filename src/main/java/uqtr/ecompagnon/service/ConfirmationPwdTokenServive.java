package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationPwdToken;


import java.util.Optional;

public interface ConfirmationPwdTokenServive {

    void saveConfirmationToken(ConfirmationPwdToken token);
    Optional<ConfirmationPwdToken> findByToken(String token);

    Optional<ConfirmationPwdToken> findByAppUser(AppUser appUser);


}
