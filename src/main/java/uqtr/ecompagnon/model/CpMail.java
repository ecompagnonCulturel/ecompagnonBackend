package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cp_Email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CpMail {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "cpMail_generator")
    @SequenceGenerator(name = "cpMail_generator", sequenceName = "cpmail_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;

    @Column(name = "cpeMail")
    private String cpeMail;

    @Column(name = "cpeCP")
    private String cpeCP;


}
