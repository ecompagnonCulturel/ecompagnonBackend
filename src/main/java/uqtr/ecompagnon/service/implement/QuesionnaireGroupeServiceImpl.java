package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.model.Questionnaire;
import uqtr.ecompagnon.model.QuestionnaireGroupe;
import uqtr.ecompagnon.model.Session;
import uqtr.ecompagnon.repository.QuestionQuestionnaireRepository;
import uqtr.ecompagnon.repository.QuestionnaireGroupeRepository;
import uqtr.ecompagnon.repository.QuestionnaireRepository;
import uqtr.ecompagnon.service.QuestionnaireGroupeService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Transactional
@Service
@AllArgsConstructor
public class QuesionnaireGroupeServiceImpl implements QuestionnaireGroupeService {
    private QuestionnaireGroupeRepository questionnaireGroupeRepository;
    private QuestionnaireRepository questionnaireRepository;
    private QuestionQuestionnaireRepository questionQuestionnaireRepository;
    private GroupEtudiantServiceImpl groupEtudiantService;
    private ActiviteServiceImpl activiteService;
    private SessionServiceImpl sessionService;
    private ComplementServiceImpl complementService;

    @Override
    public <S extends QuestionnaireGroupe> S save(QuestionnaireGroupe questionnaireGroupe) {
        return null;
    }

    @Override
    public <S extends QuestionnaireGroupe> S saveNew(QuestionnaireGroupe questionnaireGroupe) {
        return null;
    }

    @Override
    public <S extends QuestionnaireGroupe> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Iterable<QuestionnaireGroupe> findAll() {
        return null;
    }

    @Override
    public List<Questionnaire> getBySessionAndStatus(Long session, Long status) {
        Session sessionObj=sessionService.getById(session)
                .orElseThrow(() ->
                        new AppRequestException("Cette session n'existe pas"));
        return questionnaireGroupeRepository.getBySessionAndStatus(sessionObj,status);
    }

    @Override
    public List<Questionnaire> getByTypeAndGroupEtudiantAndSession(String type, Long groupeEtudiant, Long session) {
        Session sessionObj=sessionService.getById(session)
                .orElseThrow(() ->
                        new AppRequestException("Cette session n'existe pas"));

        GroupEtudiant groupeEtudiantObj=groupEtudiantService.findById(groupeEtudiant)
                .orElseThrow(() ->
                        new AppRequestException("Ce groupe n'existe pas"));

        return questionnaireGroupeRepository.getByTypeAndGroupEtudiantAndSession(type,groupeEtudiantObj,sessionObj);
    }

    @Override
    public List<Questionnaire> getQuestionnaireByGroupEtudiant(Long groupEtudiant) {
        GroupEtudiant groupeEtudiantObj=groupEtudiantService.findById(groupEtudiant)
                .orElseThrow(() ->
                        new AppRequestException("Ce groupe n'existe pas"));
        return questionnaireGroupeRepository.getQuestionnaireByGroupEtudiant(groupeEtudiantObj);
    }

    @Override
    public List<Questionnaire> getQuestionnaireByDate(LocalDateTime dateAffiche) {
        return questionnaireGroupeRepository.getQuestionnaireByDate(dateAffiche);
    }

    @Override
    public List<Questionnaire> getQuestionnaireByDateAndSession(LocalDateTime dateAffiche, Long  session) {
        Session sessionObj=sessionService.getById(session)
                .orElseThrow(() ->
                        new AppRequestException("Cette session n'existe pas"));

        return questionnaireGroupeRepository.getQuestionnaireByDateAndSession(dateAffiche,sessionObj);
    }

    @Override
    public List<GroupEtudiant> getQuestionnaireGroupEtudiant() {
        return questionnaireGroupeRepository.getQuestionnaireGroupEtudiant();
    }

    @Override
    public void deleteById(Long id) {

    }
}
