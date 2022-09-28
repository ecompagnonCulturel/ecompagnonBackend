package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import uqtr.ecompagnon.model.AppUser;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserListDTO extends AppUser {
    private Long idUsers;
    private String lastname;
    private String firstname;
    private String mailUsers;
    private String CPUsers;
    private  String formField;
}
