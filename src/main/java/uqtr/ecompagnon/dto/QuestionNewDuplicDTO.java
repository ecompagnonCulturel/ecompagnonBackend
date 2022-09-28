package uqtr.ecompagnon.dto;

import uqtr.ecompagnon.model.Question;

public interface QuestionNewDuplicDTO {
    Long getId();
    String getQuestType();
    String getQuestCate();
    String getQuestModalite();
    String getQuestTypeRepons();
    String getQuestDesc();
    String getQuestFilleType();
    String getQuestFilleModalite();
    String getQuestTypeReponsFille();
    String getQuestFilleDesc();
    Long getQuestStatus();
    Long getQuestOrdre();
    Long getOrdre();

}
