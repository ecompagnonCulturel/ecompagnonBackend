package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.QuestionNewDuplicDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.*;
import uqtr.ecompagnon.service.QuestionQuestionnaireService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class QuestionQuestionnaireServiceImpl implements QuestionQuestionnaireService {
   private QuestionQuestionnaireRepository questionQuestionnaireRepository;
    private QuestionnaireRepository questionnaireRepository;
    private SessionRepository sessionRepository;
    private GroupEtudiantServiceImpl groupEtudiantService;
    private ReponseRepository reponseRepository;
    private AppUserRepository appUserRepository;
    private  EtudiantServiceImpl etudiantService;
    private  EtudiantGroupeRepository etudiantGroupeRepository;

    @Override
    public List<QuestionQuestionnaire> getByQuestionnaireTypeAndStatus(String type, Long status) {

        return questionQuestionnaireRepository.getByQuestionnaireTypeAndStatus(type,status);

    }

    @Override
    public List<QuestionQuestionnaire> getByTypeContainingAndStatusAndId(String type, Long status,Long id) {
        Questionnaire questionnaire=questionnaireRepository.getByTypeContainingAndStatusAndId(type,status,id);

        return questionQuestionnaireRepository.getByQuestionnaire(questionnaire);
    }


    @Override
    public List<QuestionQuestionnaire> getByQuestionnaireDateTypeSession(LocalDateTime dateAffiche, LocalDateTime dateAffiche1, Long session) {
        Session session1=sessionRepository.findById(session)
                .orElseThrow(()->
                        new IllegalStateException(
                                "Session not found"
                        ));;
        Questionnaire questionnaire=questionnaireRepository.getByStartDateLessThanEqualAndEndDateGreaterThanAndSession(dateAffiche,dateAffiche1,session1);

        return questionQuestionnaireRepository.getByQuestionnaire(questionnaire);
    }

    @Override
    public List<QuestionQuestionnaire> getByQuestionnaireTypeAndDate(String type, LocalDateTime dateAffiche, LocalDateTime dateAffiche1) {
        List<QuestionQuestionnaire> questionQuestionnaires=questionQuestionnaireRepository.getByQuestionnaireTypeAndDate(type,dateAffiche,dateAffiche1);
        if(questionQuestionnaires.size()>0)
        {
          
            return questionQuestionnaires;
        }
        else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<QuestionQuestionnaire> getAllQuestionQuestionnaire() {

        return questionQuestionnaireRepository.findAll();
    }

    @Override
    public List<QuestionNewDuplicDTO> getQuestionByTypeQuestionnaire(String type) {
        return questionQuestionnaireRepository.getQuestionByTypeQuestionnaire(type);
    }

    @Override
    public List<QuestionQuestionnaire> getQuestionQByDateSessionCP(LocalDateTime dateAffiche, LocalDateTime dateAffiche1, Long session, String cp) {
        List<QuestionQuestionnaire> questionQuestionnairesT11=questionQuestionnaireRepository.getQuestionQByDateSessionCP(dateAffiche,dateAffiche1,session,cp ,"T11");

        if(questionQuestionnairesT11.size()>0)
        {
            return questionQuestionnairesT11;
        }
        else
        { List<QuestionQuestionnaire> questionQuestionnairesT12=questionQuestionnaireRepository.getQuestionQByDateSessionCPT12(dateAffiche,dateAffiche1,session,cp, "T12");
            if(questionQuestionnairesT12.size()>0)
            {
                return questionQuestionnairesT12;
            }
            else {
                String year= String.valueOf(dateAffiche1.getYear());
                List<QuestionQuestionnaire> questionQuestionnairesT3=questionQuestionnaireRepository.getQuestionQByDateSessionCPT3(dateAffiche,dateAffiche1,
                        year,cp, "T3");
                if(questionQuestionnairesT3.size()>0)
                {
                    return questionQuestionnairesT3;
                }
                else
                {
                    return new ArrayList<>();
                }

            }
        }



    }

}
