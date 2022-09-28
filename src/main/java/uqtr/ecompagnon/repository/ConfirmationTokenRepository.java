package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationToken;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository
        extends JpaRepository <ConfirmationToken,Long> {

    Optional <ConfirmationToken> findByToken(String token);

    Optional <ConfirmationToken> findByAppUser(AppUser appUser);
}
