package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {
    private final long id;
    private final String accName;
    private final String accPassword;

}
