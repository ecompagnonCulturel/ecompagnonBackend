package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uqtr.ecompagnon.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findByMailUsers(String mailuser);


    List<Users> findByCPUsers(String cpuser);

    boolean existsUsersByMailUsersAndAndCPUsers(String mail, String CP);


    List<Users> findByMailUsersAndCPUsers(String mailuser, String cpuser);

    List<Users> findByNameUsers(String nameUser);

    @Query("select u from Users u where u.nameUsers=:nameUser")
    Optional<Users> findByName(String nameUser);
}
