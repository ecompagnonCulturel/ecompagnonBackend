package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Account;
import uqtr.ecompagnon.model.Profile;
import uqtr.ecompagnon.model.Users;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    List<Account> findByAccUserAndAccProfile(Users user, Profile profile);

}
