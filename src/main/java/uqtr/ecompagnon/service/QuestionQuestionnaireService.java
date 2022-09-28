package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.QuestionNewDuplicDTO;
import uqtr.ecompagnon.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionQuestionnaireService {

    List<QuestionQuestionnaire> getByQuestionnaireTypeAndStatus(String type, Long status);
    List<QuestionQuestionnaire> getByTypeContainingAndStatusAndId(String type, Long status,Long id);
    List<QuestionQuestionnaire> getByQuestionnaireDateTypeSession(LocalDateTime dateAffiche,
                                                                  LocalDateTime dateAffiche1,
                                                                  Long session);
    List<QuestionQuestionnaire> getByQuestionnaireTypeAndDate(String type,LocalDateTime dateAffiche,
                                                              LocalDateTime dateAffiche1);
    List<QuestionQuestionnaire> getAllQuestionQuestionnaire();
    List<QuestionNewDuplicDTO> getQuestionByTypeQuestionnaire(String type);
    List<QuestionQuestionnaire> getQuestionQByDateSessionCP(LocalDateTime dateAffiche,
                                                            LocalDateTime dateAffiche1,
                                                            Long session, String cp);

}
