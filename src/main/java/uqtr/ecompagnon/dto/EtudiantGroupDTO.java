package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.GroupEtudiant;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantGroupDTO {
    private GroupEtudiant groupEtudiant;
    private List<Long> etudiantId;
}
