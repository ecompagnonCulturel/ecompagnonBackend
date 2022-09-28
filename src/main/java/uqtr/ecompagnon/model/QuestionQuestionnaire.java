package uqtr.ecompagnon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "questionQuestionnaire")
//@Table(name = "questionQuestionnaire")
public class QuestionQuestionnaire {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "questionQuestionnaire_generator")
    @SequenceGenerator(name = "questionQuestionnaire_generator", sequenceName = "questionquestionnaire_sequence", allocationSize = 1)
    @Column(name = "id")private Long id;
    @ManyToOne()
    @JoinColumn(name = "question", referencedColumnName = "id")
    private Question question;
    @ManyToOne()
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire  questionnaire;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
    private Long statut;
    private Long questOrdre;
}
