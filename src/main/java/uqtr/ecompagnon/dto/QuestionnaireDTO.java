package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.model.Question;
import uqtr.ecompagnon.model.Session;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnaireDTO {
    private long id;
    private long  activite;
    private long groupEtudiant;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    private long session;
    private String  type;
    private long status;
    private long questionnaire;
}
