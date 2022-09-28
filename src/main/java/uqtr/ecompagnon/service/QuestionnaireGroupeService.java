package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.QuestionnaireDTO;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.model.Questionnaire;
import uqtr.ecompagnon.model.QuestionnaireGroupe;
import uqtr.ecompagnon.model.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionnaireGroupeService {
    <S extends QuestionnaireGroupe> S save(QuestionnaireGroupe questionnaireGroupe);
    <S extends QuestionnaireGroupe> S saveNew(QuestionnaireGroupe questionnaireGroupe);
    <S extends QuestionnaireGroupe> Iterable<S> saveAll(Iterable<S> iterable);
    Iterable<QuestionnaireGroupe> findAll();
    List<Questionnaire> getBySessionAndStatus(Long session, Long status);
    List<Questionnaire> getByTypeAndGroupEtudiantAndSession(String type,Long groupeEtudiant, Long session);
    List<Questionnaire> getQuestionnaireByGroupEtudiant(Long groupEtudiant);
    List<Questionnaire> getQuestionnaireByDate(LocalDateTime dateAffiche);
    List<Questionnaire> getQuestionnaireByDateAndSession(LocalDateTime dateAffiche,Long session);
    List<GroupEtudiant> getQuestionnaireGroupEtudiant();
    void deleteById(Long id);
}
