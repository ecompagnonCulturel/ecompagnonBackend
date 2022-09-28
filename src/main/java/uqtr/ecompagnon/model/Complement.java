package uqtr.ecompagnon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "complement")
public class Complement {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "complement_generator"
    )
    @SequenceGenerator(
            name = "complement_generator",
            sequenceName = "complement_sequence",
            allocationSize = 1
    )
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionnaireGroupe", referencedColumnName = "id")
    private QuestionnaireGroupe questionnaireGroupe;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activite", referencedColumnName = "id")
    private Activite activite;
    private LocalDateTime endDate;
    private Long status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cours", referencedColumnName = "id")
    private Cours cours;

}
