package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "prof")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prof {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "prof_generator")
    @SequenceGenerator(name = "prof_generator", sequenceName = "note_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;
    @Column(name = "profFirstName")
    private String  profFirstName;
    @Column(name = "profLastName")
    private String  profLastName;
    @Column(name = "profContact")
    private String  profContact;
    @Column(name = "profAdresse")
    private String  profAdresse;
    @Column(name = "profDiplome")
    private String  profDiplome;
    @Column(name = "profStatus")
    private long profStatus;
}
