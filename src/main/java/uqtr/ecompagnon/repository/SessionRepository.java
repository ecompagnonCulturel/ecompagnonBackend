package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Questionnaire;
import uqtr.ecompagnon.model.Session;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session findBySessStatus(Long sessStatus);

    @Query("select  s from session s order by s.sessStart desc")
    List<Session> getSessionOrderByStartDate();

}
