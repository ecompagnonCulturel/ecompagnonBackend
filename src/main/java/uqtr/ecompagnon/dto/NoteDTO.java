package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Resources;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private long id;
    private long noteUser;
    private long noteResource;
    private String noteDate;
    private String noteDesc;
    private long noteStatus;

    public NoteDTO(long noteUser, long noteResource, String noteDate, String noteDesc, long noteStatus) {
        this.noteUser = noteUser;
        this.noteResource = noteResource;
        this.noteDate = noteDate;
        this.noteDesc = noteDesc;
        this.noteStatus = noteStatus;
    }
}
