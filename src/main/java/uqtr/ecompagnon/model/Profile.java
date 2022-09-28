package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "profNom")
    private String profNom;

    @Column(name = "profStatus")
    private long profStatus;

    /*@ManyToOne
    @JoinColumn(name="account", nullable=false)
    private Account account;*/


    @Override
    public String toString() {
        return "1" + getProfNom() + "   2" + getId()
                + "   3" + getProfStatus() + "]";
    }
}
