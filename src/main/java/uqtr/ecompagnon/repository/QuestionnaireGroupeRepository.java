package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.model.Questionnaire;
import uqtr.ecompagnon.model.QuestionnaireGroupe;
import uqtr.ecompagnon.model.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionnaireGroupeRepository extends JpaRepository<Questionnaire, Long> {

    @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and qg.status=:status and qg.session=:session")
    List<Questionnaire> getBySessionAndStatus(Session session, Long status);

    @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and  qg.startDate<:dateAffiche and qg.endDate>=:dateAffiche")
    List<Questionnaire> getQuestionnaireByDate(LocalDateTime dateAffiche);


    @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and  qg.startDate<:dateAffiche and qg.endDate>=:dateAffiche and qg.session=:session")
    List<Questionnaire> getQuestionnaireByDateAndSession(LocalDateTime dateAffiche,Session session);

    @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and qg.session=:session and qg.groupEtudiant=:groupEtudiant and q.type=:type")
    List<Questionnaire> getByTypeAndGroupEtudiantAndSession(String type, GroupEtudiant groupEtudiant, Session session);

    @Query("select distinct g from questionnairegroupe q ,groupEtudiant g where g.id=q.groupEtudiant and g.id=:groupEtudiant")
    List<Questionnaire> getQuestionnaireByGroupEtudiant(GroupEtudiant groupEtudiant);

    @Query("select distinct g from questionnairegroupe q ,groupEtudiant g where g.id=q.groupEtudiant")
    List<GroupEtudiant> getQuestionnaireGroupEtudiant();

    void deleteById(Long id);








   /* @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and qg.status=:status and q.type=:type")
    Questionnaire  getByTypeContainingAndStatus(String type,Long status);

    @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and qg.status=:status and q.type=:type and q.id=:id")
    Questionnaire  getByTypeContainingAndStatusAndId(String type,Long status,Long id);


    @Query("select distinct q from questionnairegroupe qg ,questionnaire q where q.id=qg.questionnaire and qg.session=:session and qg.startDate<:dateAffiche and qg.endDate>=:dateAffiche")
    Questionnaire getQuestionnaireByDateAndBySession(LocalDateTime dateAffiche,Session session);
*/

}
