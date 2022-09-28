package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.Session;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupEtudiantDTO {
    private long id;
    private String groupName;
    private LocalDate groupDateCreate;
    private long groupSession;
    private long groupStatus;
}
