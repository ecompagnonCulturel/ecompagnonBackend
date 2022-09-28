package uqtr.ecompagnon.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class EtudiantDTO {
    private  long id;
    private  String etudFirstName;
    private  String etudLastName;
    private  String etudCP;
    private  String etudAdresse;
}
