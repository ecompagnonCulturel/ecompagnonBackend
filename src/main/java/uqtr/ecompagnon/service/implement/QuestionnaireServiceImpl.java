package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.QuestionNewDuplicDTO;
import uqtr.ecompagnon.dto.QuestionnaireDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.*;
import uqtr.ecompagnon.service.QuestionnaireService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private QuestionnaireRepository questionnaireRepository;
    private QuestionQuestionnaireRepository questionQuestionnaireRepository;
    private GroupEtudiantServiceImpl groupEtudiantService;
    private ActiviteServiceImpl activiteService;
    private SessionServiceImpl sessionService;
    private ComplementServiceImpl complementService;
    private ComplementRepository complementRepository;
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public <S extends Questionnaire> S save(QuestionnaireDTO questionnaireDTO) {
        GroupEtudiant groupEtudiant=groupEtudiantService.findById(questionnaireDTO.getGroupEtudiant())
                .orElseThrow(() ->
                        new AppRequestException("Ce groupe n'existe pas"));

        Session session=sessionService.getById(questionnaireDTO.getSession())
                .orElseThrow(() ->
                new AppRequestException("Cette session n'existe pas"));

        if((questionnaireDTO.getActivite()!=0)||(questionnaireDTO.getType()=="T12"))
        {
            System.out.println(questionnaireDTO.getActivite());
            System.out.println(questionnaireDTO.getId());

            Activite activite=activiteService.findById(questionnaireDTO.getActivite())
                    .orElseThrow(() ->
                            new AppRequestException("Cette activité n'existe pas"));

            Complement complement= complementService.getByQuestionnaire(questionnaireDTO.getId());
            complement.setActivite(activite);
            complement.setStatus(1L);
            complementService.save(complement);
        }


        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setId(questionnaireDTO.getId());
        questionnaire.setSession(session);
        questionnaire.setGroupEtudiant(groupEtudiant);
        questionnaire.setEndDate(questionnaireDTO.getEndDate());
        questionnaire.setStartDate(questionnaireDTO.getStartDate());
        questionnaire.setType(questionnaireDTO.getType());
        questionnaire.setStatus(questionnaireDTO.getStatus());
        questionnaire.setActivite(0L);

        return (S) questionnaireRepository.save(questionnaire);
    }

    @Override
    @Transactional
    public <S extends Questionnaire> S saveNew(QuestionnaireDTO questionnaireDTO) {

        ArrayList<QuestionQuestionnaire> questionQuestionnaires=new ArrayList<QuestionQuestionnaire>();
        GroupEtudiant groupEtudiant=groupEtudiantService.findById(questionnaireDTO.getGroupEtudiant())
                .orElseThrow(() ->
                        new AppRequestException("Ce groupe n'existe pas"));

        Session session=sessionService.getById(questionnaireDTO.getSession())
                .orElseThrow(() ->
                        new AppRequestException("Cette session n'existe pas"));

       Questionnaire questionnaire=new Questionnaire();
        questionnaire.setId(0L);
        questionnaire.setSession(session);
        questionnaire.setGroupEtudiant(groupEtudiant);
        questionnaire.setEndDate(questionnaireDTO.getEndDate());
        questionnaire.setStartDate(questionnaireDTO.getStartDate());
        questionnaire.setType(questionnaireDTO.getType());
        questionnaire.setStatus(questionnaireDTO.getStatus());

         long id=questionnaireRepository.save(questionnaire).getId();

        Questionnaire questionnaireForeign=questionnaireRepository.findById(id)
                .orElseThrow(() ->
                        new AppRequestException("Ce questionnaire n'existe pas"));

        if((questionnaireDTO.getActivite()!=0)||(questionnaireDTO.getType()=="T12"))
        {
            Activite activite=activiteService.findById(questionnaireDTO.getActivite())
                    .orElseThrow(() ->
                            new AppRequestException("Cette activité n'existe pas"));

            Complement complement= new Complement();
            complement.setActivite(activite);
            complement.setStatus(1L);
            complement.setQuestionnaire(questionnaireForeign);
            complementService.save(complement);

        }

        List<QuestionNewDuplicDTO> questionNewDuplicDTOS =questionQuestionnaireRepository.getQuestionByIdQuestionnaire(questionnaireDTO.getQuestionnaire());

        questionNewDuplicDTOS.forEach(questionNewDuplicDTO->{
            Question question=new Question();
           // System.out.println(questionNewDuplicDTO.getId());
        //    System.out.println(questionNewDuplicDTO.getOrdre());
            question.setId(questionNewDuplicDTO.getId());
            question.setQuestType(questionNewDuplicDTO.getQuestType());
            question.setQuestCate(questionNewDuplicDTO.getQuestCate());
            question.setQuestModalite(questionNewDuplicDTO.getQuestModalite());
            question.setQuestTypeRepons(questionNewDuplicDTO.getQuestTypeRepons());
            question.setQuestDesc(questionNewDuplicDTO.getQuestDesc());
            question.setQuestFilleType(questionNewDuplicDTO.getQuestFilleType());
            question.setQuestFilleModalite(questionNewDuplicDTO.getQuestFilleModalite());
            question.setQuestTypeReponsFille(questionNewDuplicDTO.getQuestTypeReponsFille());
            question.setQuestFilleDesc(questionNewDuplicDTO.getQuestFilleDesc());
            question.setQuestStatus(questionNewDuplicDTO.getQuestStatus());
            question.setQuestOrdre(questionNewDuplicDTO.getQuestOrdre());


           QuestionQuestionnaire questionQuestionnaire=new QuestionQuestionnaire();
            questionQuestionnaire.setId(0L);
            questionQuestionnaire.setQuestionnaire(questionnaireForeign);
            questionQuestionnaire.setQuestion(question);
            questionQuestionnaire.setSession(questionnaireForeign.getSession());
            questionQuestionnaire.setStatut(1L);
            questionQuestionnaire.setQuestOrdre(questionNewDuplicDTO.getOrdre());

            questionQuestionnaires.add(questionQuestionnaire);

        });
        questionQuestionnaireRepository.saveAll(questionQuestionnaires);

        return (S) questionnaireForeign;
    }

    @Override
    public <S extends Questionnaire> Iterable<S> saveAll(Iterable<S> iterable) {
        return questionnaireRepository.saveAll(iterable);
    }

    @Override
    public Iterable<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public List<Questionnaire> getBySessionAndStatus(Session session, Long status) {
        return questionnaireRepository.getBySessionAndStatus(session,status);
    }

    @Override
    public List<Questionnaire> getByTypeAndGroupEtudiantAndSession(String type, Long groupeEtudiant, Long session) {
       GroupEtudiant groupEtudiant=groupEtudiantService.findById(groupeEtudiant)
               .orElseThrow(() ->
                       new AppRequestException("Ce groupe n'existe pas"));

       Session sessionObj=sessionService.getById(session)
               .orElseThrow(() ->
                       new AppRequestException("Ce session n'existe pas"));

        return questionnaireRepository.getByTypeAndGroupEtudiantAndSession(type,groupEtudiant,sessionObj);
    }


    @Override
    public List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionAndGroupEtudiant(LocalDateTime dateAffiche,LocalDateTime dateAffiche1,Long session,Long groupEtudiant) {
        GroupEtudiant groupEtudiantRec=groupEtudiantService.findById(groupEtudiant)
                .orElseThrow(() ->
                        new AppRequestException("Ce groupe n'existe pas"));

        Session sessionRec=sessionService.getById(session)
                .orElseThrow(() ->
                        new AppRequestException("Ce session n'existe pas"));

        return questionnaireRepository.getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionAndGroupEtudiant(dateAffiche,dateAffiche1,sessionRec,groupEtudiantRec);
    }

    @Override
    public List<Questionnaire> getByTypeContainingStartDateLessThanEqualAndEndDateGreaterThan(String type, LocalDateTime dateAffiche, LocalDateTime dateAffiche1) {
        return questionnaireRepository.getByTypeContainingAndStartDateLessThanEqualAndEndDateGreaterThan(type,dateAffiche,dateAffiche1);
    }

    @Override
    public List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSession(LocalDateTime dateAffiche, LocalDateTime dateAffiche1, Long session) {
        return questionnaireRepository.getByStartDateLessThanEqualAndEndDateGreaterThanAndSession(dateAffiche,dateAffiche1,session);
    }

    @Override
    public List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotif(LocalDateTime dateAffiche, LocalDateTime dateAffiche1,LocalDate dateDujour, Long session) {
        return questionnaireRepository.getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotif(dateAffiche,dateAffiche1,dateDujour, session);
    }

    @Override
    public List<Questionnaire> getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotifT3(LocalDateTime dateAffiche, LocalDateTime dateAffiche1,LocalDate dateDujour) {
        return questionnaireRepository.getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotifT3(dateAffiche,dateAffiche1,dateDujour);
    }


   /* @Override
    public List<QuestionQuestionnaire> getByTypeAndStatus(String type, Long status) {
        Questionnaire questionnaire=questionnaireRepository.getByTypeContainingAndStatus(type,status);

        return questionQuestionnaireRepository.getByQuestionnaire(questionnaire);
    }*/

    @Override
    public List<Questionnaire> getQuestionnaireByStartDateAndEndDate(LocalDateTime dateAffiche,LocalDateTime dateAffiche1) {
        return questionnaireRepository.getQuestionnaireByStartDateOrEndDate(dateAffiche,dateAffiche1);
    }

    @Override
    public List<Questionnaire> getQuestionnaireByGroupEtudiant(Long groupEtudiant) {
        GroupEtudiant groupEtudiantSend=groupEtudiantService.findById(groupEtudiant)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Groupe Etudiant not found"
                        ));
        return questionnaireRepository.getQuestionnaireByGroupEtudiant(groupEtudiantSend);
    }

    @Override
    public List<GroupEtudiant> getQuestionnaireGroupEtudiant() {
        return questionnaireRepository.getQuestionnaireGroupEtudiant();
    }


    @Override
    public void deleteById(Long questionnaire,Long session) {
        try {

            complementRepository.deleteByQuestionnaireAndSession(questionnaire,session);
            notificationRepository.deleteByQuestionnaireAndSession(questionnaire,session);
            questionQuestionnaireRepository.deleteByQuestionnaireAndSession(questionnaire,session);

        }
        catch (Exception e)
        {
           System.out.println(e);
        }
        questionnaireRepository.deleteById(questionnaire);

    }

   /* @Override
    public List<GroupEtudiant> getByTypeT2StartDateEqualAndSession(LocalDate dateAffiche, Long session) {
        return questionnaireRepository.getByTypeT2StartDateEqualAndSession(dateAffiche,session);
    }

    @Override
    public List<GroupEtudiant> getByTypeT11AndT12StartDateEqualAndSession(LocalDate dateAffiche, Long session) {
        return questionnaireRepository.getByTypeT11AndT12StartDateEqualAndSession(dateAffiche,session);
    }
*/


    public List<Questionnaire> getByTypeStartDateEqualAndSession(LocalDate dateAffiche, Long session, List<String> types) {
        return questionnaireRepository.getByTypeStartDateEqualAndSession(dateAffiche,session,types);
    }

    public List<Questionnaire> getByTypeStartDateEqualT3(LocalDate dateAffiche, List<String> types) {
        return questionnaireRepository.getByTypeStartDateEqualT3(dateAffiche,types);
    }

    @Override
    public List<GroupEtudiant> getByGBTypeStartDateEqualAndSession(LocalDate dateAffiche, Long session, List<String> types) {
        return questionnaireRepository.getByGBTypeStartDateEqualAndSession(dateAffiche,session,types);
    }

}
