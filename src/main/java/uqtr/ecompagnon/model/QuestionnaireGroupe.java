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
@Entity(name = "questionnairegroupe")
public class QuestionnaireGroupe {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "questionnaireG_generator")
    @SequenceGenerator(name = "questionnaireGp_generator", sequenceName = "questionnaireGp_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupEtudiant", referencedColumnName = "id")
    private GroupEtudiant groupEtudiant;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    private Long status;

}
