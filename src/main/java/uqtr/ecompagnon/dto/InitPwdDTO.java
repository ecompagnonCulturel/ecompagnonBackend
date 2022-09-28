package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class InitPwdDTO {
    private final String token;
    private final String passwordUsers;
}
