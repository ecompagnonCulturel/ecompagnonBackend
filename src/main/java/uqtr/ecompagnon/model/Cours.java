package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cours {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cours_generator"
    )
    @SequenceGenerator(
            name = "cours_generator",
            sequenceName = "cours_sequence",
            allocationSize = 1
    )
    @Column(name = "id")
    private long id;
    @Column(name = "coursDes")
    private String coursDes;
    @Column(name = "coursNomLong")
    private String coursNomLong;
    @Column(name = "coursNomCourt")
    private String coursNomCourt;
    @Column(name = "coursStatus")
    private long coursStatus;
}
