package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppUserUpdatePwdDTO {
    private final String mailUsers;
    private final String oldPasswordAUsers;
    private final String passwordUsers;

}
