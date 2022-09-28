package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> getBySessionAndStatus(Session session,Long status);
    List<Questionnaire> getByTypeAndGroupEtudiantAndSession(String type, GroupEtudiant groupEtudiant, Session session);
    Questionnaire  getByTypeContainingAndStatus(String type,Long status);
    List<Questionnaire>  getByTypeContainingAndStartDateLessThanEqualAndEndDateGreaterThan(String type,LocalDateTime dateAffiche,LocalDateTime dateAffiche1);
    Questionnaire  getByTypeContainingAndStatusAndId(String type,Long status,Long id);
    List<Questionnaire> getQuestionnaireByStartDateOrEndDate(LocalDateTime dateAffiche,LocalDateTime dateAffiche1);
    Questionnaire getByStartDateLessThanEqualAndEndDateGreaterThanAndSession(LocalDateTime dateAffiche,LocalDateTime dateAffiche1,Session session);
    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionAndGroupEtudiant(LocalDateTime dateAffiche,LocalDateTime dateAffiche1,Session session,GroupEtudiant groupEtudiant);
    List<Questionnaire> getQuestionnaireByGroupEtudiant(GroupEtudiant groupEtudiant);
    List<Questionnaire> getQuestionnaireByIdIn(List<Long> idList);

    @Query("select distinct g from questionnaire q ,groupEtudiant g where g.id=q.groupEtudiant")
    List<GroupEtudiant> getQuestionnaireGroupEtudiant();

    @Query("select distinct q from questionnaire q  where  q.startDate<=:dateAffiche and q.endDate>=:dateAffiche1 and q.session.id=:session")
    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSession(LocalDateTime dateAffiche, LocalDateTime dateAffiche1, Long session);

    @Query("select distinct q from questionnaire q  where  q.startDate<=:dateAffiche " +
            "and q.endDate>=:dateAffiche1 and q.session.id=:session " +
            "and fisrtNotific=1 and Date(Date(lastNotific)+2)=:dateDujour ")
    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotif(LocalDateTime dateAffiche,
                                                                                             LocalDateTime dateAffiche1,
                                                                                             LocalDate dateDujour,
                                                                                                     Long session);

    @Query("select distinct q from questionnaire q  where  q.startDate<=:dateAffiche " +
            "and q.endDate>=:dateAffiche1 and q.type='T3' " +
            "and fisrtNotific=1 and Date(Date(lastNotific)+2)=:dateDujour ")
    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotifT3(LocalDateTime dateAffiche,
                                                                                             LocalDateTime dateAffiche1,
                                                                                             LocalDate dateDujour);


    @Query(value ="select distinct q.groupEtudiant from questionnaire q " +
            "where q.session.id=:session and q.type in (:types) ")
    List<GroupEtudiant> getGroupEtudiantBySessionAndType(Long session, List<String> types);

/*
    @Query("select distinct q.groupEtudiant from questionnaire q  where  DATE(q.startDate)=:dateAffiche " +
            "and q.session.id=:session and q.type in ('T2')")
    List<GroupEtudiant> getByTypeT2StartDateEqualAndSession(LocalDate dateAffiche, Long session);*/

    @Query("select distinct q.groupEtudiant from questionnaire q  where  DATE(q.startDate)=:dateAffiche " +
            "and q.session.id=:session and q.type in (:types) and q.lastNotific is null and q.fisrtNotific is null")
    List<GroupEtudiant> getByGBTypeStartDateEqualAndSession(LocalDate dateAffiche, Long session, List<String> types);

    @Query("select distinct q from questionnaire q  where  DATE(q.startDate)=:dateAffiche " +
            "and q.session.id=:session and q.type in (:types) and q.lastNotific is null and q.fisrtNotific is null")
    List<Questionnaire> getByTypeStartDateEqualAndSession(LocalDate dateAffiche, Long session, List<String> types);


    @Query("select distinct q from questionnaire q  where  DATE(q.startDate)=:dateAffiche " +
            "and q.type in (:types) and q.lastNotific is null and q.fisrtNotific is null")
    List<Questionnaire> getByTypeStartDateEqualT3(LocalDate dateAffiche, List<String> types);

    void deleteById(Long id);

    @Query("select distinct q from questionnaire q  where " +
            "q.session.id=:session and q.type in (:types) " +
            "and groupEtudiant.id=:groupEtudiant")
    Questionnaire getOneByTypeAndGroupEtudiantAndSession(String types, Long groupEtudiant, Long session);


}
