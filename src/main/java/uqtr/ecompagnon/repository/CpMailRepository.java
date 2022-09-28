package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.CpMail;

@Repository
public interface CpMailRepository extends JpaRepository<CpMail, Long> {

    CpMail findByCpeMail(String mail);

    CpMail findByCpeCP(String cp);

    void deleteByCpeCP(String cp);

    boolean existsByCpeMailAndAndCpeCP(String mail, String CP);
}
