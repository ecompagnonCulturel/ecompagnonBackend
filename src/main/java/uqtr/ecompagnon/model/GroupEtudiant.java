package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groupEtudiant")
public class GroupEtudiant {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "groupEtudiant_generator")
    @SequenceGenerator(name = "groupEtudiant_generator", sequenceName = "groupetudiant_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "groupDateCreate")
    private LocalDate groupDateCreate;
    @ManyToOne()
    @JoinColumn(name = "groupSession", referencedColumnName = "id")
    private Session groupSession;
    @Column(name = "groupStatus")
    private long groupStatus;

   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<Etudiant> etudiants = new ArrayList<>();*/

}
