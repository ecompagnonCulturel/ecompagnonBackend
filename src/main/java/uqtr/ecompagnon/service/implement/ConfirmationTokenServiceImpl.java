package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationToken;
import uqtr.ecompagnon.repository.ConfirmationTokenRepository;
import uqtr.ecompagnon.service.ConfirmationTokenService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

private final ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public void saveConfirmationToken(ConfirmationToken token)
    {

        confirmationTokenRepository.save(token);
    }
@Override
    public Optional<ConfirmationToken> findByToken(String token) throws IllegalStateException {
        return confirmationTokenRepository.findByToken(token);
    }

@Override
    public Optional<ConfirmationToken> findByAppUser(AppUser appUser) throws IllegalStateException {
        return confirmationTokenRepository.findByAppUser(appUser);
    }

}
