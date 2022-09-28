package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "users_generator")
    @SequenceGenerator(name = "users_generator", sequenceName = "users_sequence", allocationSize = 1)

    @Column(name = "idUsers")
    private long idUsers;


    @Column(name = "nameUsers")
    private String nameUsers;

    @Column(name = "mailUsers")
    private String mailUsers;

    @Column(name = "CPUsers")
    private String CPUsers;

    @Column(name = "passwordUsers")
    private String passwordUsers;


    @Column(name = "dateModifUsers")
    private Date dateModifUsers;


    @Column(name = "statusUsers")
    private Long statusUsers;

    @Override
    public String toString() {
        return "1" + getNameUsers() + "   2" + getMailUsers()
                + "   3" + getCPUsers() + "  4" + getPasswordUsers() + "  5" + getDateModifUsers() + "  6" + getClass() + "]";
    }


  }
