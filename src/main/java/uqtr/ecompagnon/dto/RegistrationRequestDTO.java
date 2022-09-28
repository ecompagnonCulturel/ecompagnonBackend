package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestDTO {

    private final String firstname;
    private final String lastname;
    private final String mailUsers;
    private final String CPUsers;
    private final String passwordUsers;
    private final String fcmToken;




}
