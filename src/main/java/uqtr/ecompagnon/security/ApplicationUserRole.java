package uqtr.ecompagnon.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    ETUDIANT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_WRITE, ApplicationUserPermission.COURSE_READ, ApplicationUserPermission.ETUDIANT_READ, ApplicationUserPermission.ETUDIANT_WRITE)),
    ADMINREAD(Sets.newHashSet(ApplicationUserPermission.ETUDIANT_READ, ApplicationUserPermission.COURSE_READ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        // System.out.println(this.name());
        return permissions;
    }
}
