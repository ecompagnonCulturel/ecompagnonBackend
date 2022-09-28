package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uqtr.ecompagnon.model.Profile;
import uqtr.ecompagnon.model.Users;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accUser", referencedColumnName = "idUsers")
    private Users accUser;

    @ManyToOne()
    @JoinColumn(name = "accProfile", referencedColumnName = "id")
    private Profile accProfile;

    @Column(name = "accDateCreate")
    private Date accDateCreate;

    @Column(name = "accDateModif")
    private Date accDateModif;

    @Column(name = "accStatus")
    private long accStatus;

    public String toString() {
        return "1" + getAccUser().toString() + "   2" + getAccProfile()
                + "   3" + getAccDateCreate() + "  4" + getAccDateModif() + "  5" + getAccStatus() + "  6" + getClass() + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
