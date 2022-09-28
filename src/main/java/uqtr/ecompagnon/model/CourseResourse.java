package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "courseResource")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResourse {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_resourse_sequence"
    )
    @SequenceGenerator(
            name = "course_resourse_sequence",
            sequenceName = "course_resourse_sequence",
            allocationSize = 1
    )
    @Column(name = "id")
    private long id;
    @Column(name = "coursResIntention")
    private String coursResIntention;
    @ManyToOne()
    @JoinColumn(name = "coursResResource", referencedColumnName = "id")
    private Resources coursResResource;
    @ManyToOne()
    @JoinColumn(name = "coursResSession", referencedColumnName = "id")
    private Session coursResSession;
    @ManyToOne()
    @JoinColumn(name = "coursResProf", referencedColumnName = "id")
    private Prof coursResProf;
    @ManyToOne()
    @JoinColumn(name = "coursResGroup", referencedColumnName = "id")
    private GroupEtudiant coursResGroup;
    @ManyToOne()
    @JoinColumn(name = "coursResCours", referencedColumnName = "id")
    private Cours coursResCours;
    @Column(name = "coursResStartDate")
    private LocalDateTime coursResStartDate;
    @Column(name = "coursResEndDate")
    private LocalDateTime coursResEndDate;
    @Column(name = "etudAdresse")
    private String etudAdresse;
    @Column(name = "coursResStatus")
    private long coursResStatus;

}
