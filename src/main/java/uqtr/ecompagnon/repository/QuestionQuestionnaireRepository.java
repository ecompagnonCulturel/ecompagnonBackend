package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.dto.QuestionNewDuplicDTO;
import uqtr.ecompagnon.model.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuestionQuestionnaireRepository extends JpaRepository<QuestionQuestionnaire, Long> {

    List<QuestionQuestionnaire> getByQuestionnaire(Questionnaire questionnaire);

    @Query("select distinct q.question.id as id,q.question.questType as questType," +
            "q.question.questModalite as questModalite,q.question.questTypeRepons as questTypeRepons," +
            "q.question.questDesc as questDesc, q.question.questFilleType as questFilleType," +
            "q.question.questFilleDesc as questFilleDesc,q.question.questStatus as questStatus,"+
            "q.question.questOrdre as questOrdre,"+
            "q.question.questFilleModalite as questFilleModalite,q.question.QuestTypeReponsFille as QuestTypeReponsFille,"+
            "q.questOrdre as ordre from questionQuestionnaire q where q.questionnaire.type=:type")
    List<QuestionNewDuplicDTO> getQuestionByTypeQuestionnaire(@Param("type") String type);


    @Query("select distinct q.question.id as id,q.question.questType as questType," +
            "q.question.questModalite as questModalite,q.question.questTypeRepons as questTypeRepons," +
            "q.question.questDesc as questDesc, q.question.questFilleType as questFilleType," +
            "q.question.questFilleDesc as questFilleDesc,q.question.questStatus as questStatus,"+
            "q.question.questOrdre as questOrdre,"+
            "q.question.questFilleModalite as questFilleModalite,q.question.QuestTypeReponsFille as QuestTypeReponsFille,"+
            "q.questOrdre as ordre from questionQuestionnaire q where q.questionnaire.id=:id")
    List<QuestionNewDuplicDTO> getQuestionByIdQuestionnaire(@Param("id") Long id);

    @Query(value ="select * from public.\"questionQuestionnaire\" qq " +
            " where qq.\"questionnaire\" in (select q.id from public.\"questionnaire\" q, public.\"question\" qe,  " +
            "public.etudiantgroupe ep,public.etudiant e, public.\"appUser\" app,public.\"groupEtudiant\" g " +
            "where q.id=qq.questionnaire and ep.etudiant=e.id " +
            "and e.\"etudCP\"=app.\"CPUsers\" and ep.session=:session "+
            "and g.id=q.\"groupEtudiant\" and qe.id=qq.\"question\"  " +
            "and ep.groupe=g.id  " +
            "and app.\"CPUsers\"=:cp " +
            "and q.session=ep.session and q.\"startDate\"<=:dateAffiche " +
            "and q.\"endDate\">:dateAffiche1  and q.type=:type " +
            "and q.id Not IN (SELECT  (formField_data->>'id')\\:\\:int as formFieldId " +
            "FROM  public.\"appUser\" ap, json_array_elements(ap.\"formField\"\\:\\:json) AS formField_data " +
            "where ap.\"CPUsers\"=:cp))"
         , nativeQuery = true)
    List<QuestionQuestionnaire> getQuestionQByDateSessionCP(LocalDateTime dateAffiche,
                                                               LocalDateTime dateAffiche1,
                                                               Long session, String cp, String type);


    @Query(value ="select * from public.\"questionQuestionnaire\" qq " +
            " where qq.\"questionnaire\" in (select q.id from public.\"questionnaire\" q, public.\"question\" qe,  " +
            "public.etudiant e, public.\"appUser\" app " +
            "where q.id=qq.questionnaire  " +
            "and e.\"etudCP\"=app.\"CPUsers\" "+
            "and qe.id=qq.\"question\"  " +
            "and app.\"CPUsers\"=:cp and app.\"annfin\"=:nowYear " +
            "and q.\"startDate\"<=:dateAffiche " +
            "and q.\"endDate\">:dateAffiche1  and q.type=:type " +
            "and q.id Not IN (SELECT  (formField_data->>'id')\\:\\:int as formFieldId " +
            "FROM  public.\"appUser\" ap, json_array_elements(ap.\"formField\"\\:\\:json) AS formField_data " +
            "where ap.\"CPUsers\"=:cp))"
            , nativeQuery = true)
    List<QuestionQuestionnaire> getQuestionQByDateSessionCPT3(LocalDateTime dateAffiche,
                                                            LocalDateTime dateAffiche1,
                                                              String nowYear,String cp,
                                                              String type);

    @Query(value ="select * from public.\"questionQuestionnaire\" qq " +
            " where qq.\"questionnaire\" in (select q.id from public.\"questionnaire\" q, public.\"question\" qe,  " +
            "public.etudiantgroupe ep,public.etudiant e, public.\"appUser\" app,public.\"groupEtudiant\" g " +
            "where q.id=qq.questionnaire and ep.etudiant=e.id " +
            "and e.\"etudCP\"=app.\"CPUsers\" and ep.session=:session "+
            "and g.id=q.\"groupEtudiant\" and qe.id=qq.\"question\"  " +
            "and ep.groupe=g.id  " +
            "and app.\"CPUsers\"=:cp " +
            "and q.session=ep.session and q.\"startDate\"<=:dateAffiche " +
            "and q.\"endDate\">:dateAffiche1  and q.type=:type " +
            "and app.\"idUsers\" in (SELECT distinct ea.\"idUsers\" " +
                                    "FROM public.reponse r,public.questionnaire q, \"groupEtudiant\" gr,\"appUser\" ea  " +
                                    "where r.questionnaire=q.id " +
                                    "and gr.id=q.\"groupEtudiant\" " +
                                    "and r.session=q.session " +
                                    "and r.cp= ea.\"idUsers\" " +
                                    "and r.session=:session  " +
                                    "and q.type='T11' and ea.\"CPUsers\"=:cp)  " +
            "and q.id Not IN (SELECT  (formField_data->>'id')\\:\\:int as formFieldId " +
                            "FROM  public.\"appUser\" ap, json_array_elements(ap.\"formField\"\\:\\:json) AS formField_data " +
                            "where ap.\"CPUsers\"=:cp)" +
            ")"
            , nativeQuery = true)
    List<QuestionQuestionnaire> getQuestionQByDateSessionCPT12(LocalDateTime dateAffiche,
                                                            LocalDateTime dateAffiche1,
                                                            Long session, String cp, String type);



    @Query(value ="select * from public.\"questionQuestionnaire\" qq " +
            "where qq.\"questionnaire\" in (select q.id from public.\"questionnaire\" q " +
            "where q.id=qq.questionnaire " +
            "and q.\"startDate\"<=:dateAffiche " +
            "and q.\"endDate\">:dateAffiche1  and q.type=:type )"
            , nativeQuery = true)
    List<QuestionQuestionnaire> getByQuestionnaireTypeAndDate(String type, LocalDateTime dateAffiche,
                                                              LocalDateTime dateAffiche1);

    @Query(value ="select * from public.\"questionQuestionnaire\" qq " +
            "where qq.\"questionnaire\" in (select q.id from public.\"questionnaire\" q " +
            "where q.id=qq.questionnaire " +
            " and q.type=:type and q.\"status\" =:status)"
            , nativeQuery = true)
    List<QuestionQuestionnaire> getByQuestionnaireTypeAndStatus(String type, Long status);


    @Modifying
    @Transactional
    @Query("delete from questionQuestionnaire q where q.questionnaire in " +
            "(select q from questionnaire q " +
            "where q.id=:questionnaire " +
            " and q.session.id=:session)")
    void deleteByQuestionnaireAndSession(Long questionnaire,Long session);




}
