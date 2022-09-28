package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.Activite;
import uqtr.ecompagnon.model.Complement;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.model.Questionnaire;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ComplementRepository extends JpaRepository<Complement, Long> {

    Complement  getByQuestionnaire(Questionnaire questionnaire);

    @Query("select distinct a from activite a,complement c where a.id=c.activite")
    List<Activite> getAllActiviteComple();

    @Query("select distinct c.activite from complement c")
    List<Activite> getAllActivitiesFromComplement();


    @Modifying
    @Transactional
    @Query("delete from complement c where c.questionnaire in (select q from questionnaire q " +
                                                                "where q.id=:questionnaire " +
                                                                "and q.session.id=:session)")
    void deleteByQuestionnaireAndSession(Long questionnaire,Long session);


}
