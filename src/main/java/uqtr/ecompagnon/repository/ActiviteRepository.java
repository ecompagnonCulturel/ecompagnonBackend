package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.*;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    List<Activite> getBySession(Session session);


}
