package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uqtr.ecompagnon.model.*;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {

    @Query("select distinct q from reponse r, questionnaire  q where q.id=r.questionnaire")
    List<Questionnaire> getAllQuestionnaireResponse();

    @Query("select distinct q from reponse r, questionnaire  q where q.id=r.questionnaire and r.session=:session")
    List<Questionnaire> getQuestionnaireResponseBySession(Session session);

    @Query("select distinct q from reponse r, questionnaire  q " +
            "where q.id=r.questionnaire and r.cp=:appUser")
    List<Questionnaire> getQuestionnaireResponseByUser(AppUser appUser);

    List<Reponse> getReponseByQuestionnaireOrderByCp(Questionnaire questionnaire);

    @Query("select distinct r from reponse r, questionnaire  q, session s " +
            "where q.id=r.questionnaire and s.id=r.session and  q.id=:questionnaire and s.id=:session")
    List<Reponse> getReponseByQuestionnaireAndSession(Long questionnaire,Long session);



    @Query("select r from reponse r, " +
            " appUser app, " +
            "etudiant e, " +
            "etudiantgroupe eg " +
            "where r.cp.idUsers=app.idUsers " +
            "and e.id=eg.etudiant.id " +
            "and app.CPUsers=e.etudCP " +
            "and r.session=eg.session " +
            "and e.id=:idEtud " +
            "and r.session.id=:session " +
            "and eg.groupEtudiant.id=:group")
    List<Reponse> getReponseByGroupAndSessionAndIdEtud(Long group,Long session,Long idEtud);


    @Query("select distinct  eg.etudiant from reponse r, " +
            " appUser app, " +
            "etudiant  e ," +
            "etudiantgroupe eg " +
            "where r.cp.idUsers=app.idUsers " +
            " and app.CPUsers=e.etudCP " +
            "and e.id=eg.etudiant.id " +
            "and r.session=eg.session " +
            "and r.session.id=:session " +
            "and eg.groupEtudiant.id=:group")
    List<Etudiant> getEtudiantFromReponseByGroupAndSession(Long group,Long session);



    /* @Query("select distinct q from reponse r, question  q where q.id=r.questionnaire and q.session=:session")
     List<Questionnaire> getQuestionnaResponseBySession(Session session);
 */

   /* List<Question> getQuestionByQuestionnaire(Questionnaire questionnaire);

    List<AppUser> getQuestionByQuestionnaire(Questionnaire questionnaire);*/


}
