package uqtr.ecompagnon.security;

public enum ApplicationUserPermission {
    ETUDIANT_READ("etudiant:read "),
    ETUDIANT_WRITE("etudiant:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
