package uqtr.ecompagnon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import uqtr.ecompagnon.dto.JsonIntDTO;
import uqtr.ecompagnon.dto.JsonStringDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "appUser")
public class AppUser implements UserDetails {
        @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_generator"
    )
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    private Long idUsers;
    private String lastname;
    private String firstname;
    private String mailUsers;
    private String CPUsers;
    private String passwordUsers;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked=false;
    private Boolean enabled=false;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String formField;
    private String TokenNotific;
    private boolean finissant;
    private String annfin;

    public AppUser(String lastname,
                   String firstname,
                   String email,
                   String cp,
                   String password,
                   AppUserRole appUserRole
                   ) {
        this.lastname = lastname;
        this.firstname=firstname;
        this.mailUsers = email;
        this.CPUsers = cp;
        this.passwordUsers = password;
        this.appUserRole = appUserRole;

    }

    public AppUser(Long idUsers, String lastname, String firstname, String mailUsers, String CPUsers, String passwordUsers, AppUserRole appUserRole) {
        this.idUsers = idUsers;
        this.lastname = lastname;
        this.firstname = firstname;
        this.mailUsers = mailUsers;
        this.CPUsers = CPUsers;
        this.passwordUsers = passwordUsers;
        this.appUserRole = appUserRole;
        this.enabled=true;
    }

    public AppUser(String lastname, String firstname, String email, String cp, String passwordUsers, AppUserRole appUserRole, String fcmToken) {
        this.lastname = lastname;
        this.firstname=firstname;
        this.mailUsers = email;
        this.CPUsers = cp;
        this.passwordUsers = passwordUsers;
        this.appUserRole = appUserRole;
        this.TokenNotific=fcmToken;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return passwordUsers;
    }

    @Override
    public String getUsername() {
        return mailUsers;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getCp() {
        return getCPUsers();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
