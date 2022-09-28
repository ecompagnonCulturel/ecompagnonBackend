package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentialDTO {
    private  String email;
    private  String password;

    public UserCredentialDTO(String email) {
        this.email = email;
    }
}
