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
@Entity(name = "notification")
public class Notification {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "notification_generator")
    @SequenceGenerator(name = "notification_generator", sequenceName = "notification_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "groupEtudiant",referencedColumnName = "id")
    private GroupEtudiant groupEtudiant;
    private LocalDateTime DateSend;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
    @ManyToOne()
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;
    private String  type;
    private Long status;

}
