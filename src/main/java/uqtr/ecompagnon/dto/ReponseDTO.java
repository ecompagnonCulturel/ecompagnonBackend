package uqtr.ecompagnon.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ReponseDTO {
    private Long id;
    private Long question;
    private Long questionnaire;
    private Long cp;
    private String questType;
    private JsonStringDTO reponsModalite;
    private String reponsType;
    private String  reponsText;
    private Long reponsEntier;
    private String questFilleType;
    private JsonStringDTO responsFilleModalite;
    private String reponsFilleType;
    private String reponsFilleText;
    private Long   reponsEntierFille;

}
