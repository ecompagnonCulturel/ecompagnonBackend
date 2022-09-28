package uqtr.ecompagnon.dto;

import lombok.*;
import uqtr.ecompagnon.model.Reponse;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReponseRequestDTO {

    private final String CP;
    private final Long questionnaire;
    private final String type;
    private final String repQ1T0;
    private final List<Reponse> questionRep;
}
