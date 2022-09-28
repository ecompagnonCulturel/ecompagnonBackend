package uqtr.ecompagnon.service;



import uqtr.ecompagnon.dto.QuestionnaireDTO;
import uqtr.ecompagnon.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface QuestionnaireService {
    <S extends Questionnaire> S save(QuestionnaireDTO questionnaireDTO);
    <S extends Questionnaire> S saveNew(QuestionnaireDTO questionnaireDTO);
    <S extends Questionnaire> Iterable<S> saveAll(Iterable<S> iterable);
    Iterable<Questionnaire> findAll();
    List<Questionnaire> getBySessionAndStatus(Session session, Long status);
    List<Questionnaire> getByTypeAndGroupEtudiantAndSession(String type,Long groupeEtudiant, Long session);
    List<Questionnaire> getQuestionnaireByStartDateAndEndDate(LocalDateTime dateAffiche,LocalDateTime dateAffiche1);
    List<Questionnaire> getQuestionnaireByGroupEtudiant(Long groupEtudiant);
    List<GroupEtudiant> getQuestionnaireGroupEtudiant();
    void deleteById(Long questionnaire,Long session);
    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionAndGroupEtudiant(LocalDateTime dateAffiche,LocalDateTime dateAffiche1,Long session,Long groupEtudiant);
    List<Questionnaire>  getByTypeContainingStartDateLessThanEqualAndEndDateGreaterThan(String type,LocalDateTime dateAffiche,LocalDateTime dateAffiche1);
    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSession(LocalDateTime dateAffiche,LocalDateTime dateAffiche1,Long session);
   // List<GroupEtudiant> getByTypeT2StartDateEqualAndSession(LocalDate dateAffiche, Long session);
   // List<GroupEtudiant> getByTypeT11AndT12StartDateEqualAndSession(LocalDate dateAffiche, Long session);
    List<Questionnaire> getByTypeStartDateEqualAndSession(LocalDate dateAffiche, Long session,List<String> types);
    List<GroupEtudiant> getByGBTypeStartDateEqualAndSession(LocalDate dateAffiche, Long session, List<String> types);

    List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotif(LocalDateTime dateAffiche, LocalDateTime dateAffiche1,LocalDate dateDujour, Long session);
     List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotifT3(LocalDateTime dateAffiche, LocalDateTime dateAffiche1,LocalDate dateDujour);


}
