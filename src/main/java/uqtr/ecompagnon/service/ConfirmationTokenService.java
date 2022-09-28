package uqtr.ecompagnon.service;

import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> findByToken(String token);

     Optional<ConfirmationToken> findByAppUser(AppUser appUser);

}
