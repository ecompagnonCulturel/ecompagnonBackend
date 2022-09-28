package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "etudiant")
public class Etudiant {


    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "etudiant_generator")
    @SequenceGenerator(name = "etudiant_generator", sequenceName = "etudiant_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;
    @Column(name = "etudFirstName")
    private String etudFirstName;
    @Column(name = "etudLastName")
    private String etudLastName;
    @Column(name = "etudCP")
    private String etudCP;
    @Column(name = "etudAdresse")
    private String etudAdresse;
    @Column(name = "etudContact")
    private String etudContact;
    @Column(name = "etudStatus")
    private long etudStatus;
    @Column(name = "etudDebProg")
    private String etudDebProg;
    @Column(name = "etudProg")
    private long etudProg;



}
