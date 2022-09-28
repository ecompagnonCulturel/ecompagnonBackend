package uqtr.ecompagnon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "etudiantgroupe")
public class EtudiantGroupe {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "etudiantGroupe_generator")
    @SequenceGenerator(name = "etudiantGroupe_generator", sequenceName = "etudiantgroupe_sequence", allocationSize = 1)

    private Long id;
    @ManyToOne()
    @JoinColumn(name = "etudiant", referencedColumnName = "id")
    private Etudiant etudiant;
    @ManyToOne()
    @JoinColumn(name = "groupe", referencedColumnName = "id")
    private GroupEtudiant groupEtudiant;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;
    private Long statut;
}
