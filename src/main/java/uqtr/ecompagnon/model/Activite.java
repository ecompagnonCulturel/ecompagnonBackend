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
@Entity(name = "activite")
public class Activite {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activite_generator"
    )
    @SequenceGenerator(
            name = "activite_generator",
            sequenceName = "activite_sequence",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    private String actDesc;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
    private Long type;
    private Long status;

}
