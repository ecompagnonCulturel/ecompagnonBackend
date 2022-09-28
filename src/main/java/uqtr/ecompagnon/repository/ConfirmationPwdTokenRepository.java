package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.ConfirmationPwdToken;
import uqtr.ecompagnon.model.ConfirmationToken;

import java.util.Optional;


@Repository
public interface ConfirmationPwdTokenRepository extends JpaRepository<ConfirmationPwdToken,Long>  {


    Optional<ConfirmationPwdToken> findByToken(String token);

    Optional <ConfirmationPwdToken> findByAppUser(AppUser appUser);

}
