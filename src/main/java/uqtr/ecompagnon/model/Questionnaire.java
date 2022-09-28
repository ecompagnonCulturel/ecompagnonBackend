package uqtr.ecompagnon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "questionnaire")
public class Questionnaire {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "questionnaire_generator")
    @SequenceGenerator(name = "questionnaire_generator", sequenceName = "questionnaire_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    private Long  activite;
    @ManyToOne()
    @JoinColumn(name = "groupEtudiant",referencedColumnName = "id")
    private GroupEtudiant groupEtudiant;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
    private String  type;
    private Long status;
    private Long fisrtNotific;
    private LocalDateTime lastNotific;
}
