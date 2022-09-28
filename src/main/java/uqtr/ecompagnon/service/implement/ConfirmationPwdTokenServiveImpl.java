package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationPwdToken;
import uqtr.ecompagnon.repository.ConfirmationPwdTokenRepository;
import uqtr.ecompagnon.service.ConfirmationPwdTokenServive;

import java.util.Optional;
@Service
@AllArgsConstructor
public class ConfirmationPwdTokenServiveImpl implements ConfirmationPwdTokenServive {
    private final ConfirmationPwdTokenRepository confirmationPwdTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationPwdToken token) {
        confirmationPwdTokenRepository.save(token);


    }

    @Override
    public Optional<ConfirmationPwdToken> findByToken(String token) {
        return confirmationPwdTokenRepository.findByToken(token);
    }

    @Override
    public Optional<ConfirmationPwdToken> findByAppUser(AppUser appUser) {
        return confirmationPwdTokenRepository.findByAppUser(appUser);
    }


}
