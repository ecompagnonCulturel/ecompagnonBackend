package uqtr.ecompagnon.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginResponseDTO {

    private final Long idUsers;
    private final String nameUsers;
    private final String firstname;
    private final String mailUsers;
    private final String CPUsers;
    private final String formField;
    private  String token;
    private  String fcmToken;

    public LoginResponseDTO(Long idUsers, String nameUsers, String firstname, String mailUsers, String CPUsers, String formField,String fcmToken) {
        this.idUsers = idUsers;
        this.nameUsers = nameUsers;
        this.firstname = firstname;
        this.mailUsers = mailUsers;
        this.CPUsers = CPUsers;
        this.formField = formField;
        this.fcmToken = fcmToken;
    }
}
