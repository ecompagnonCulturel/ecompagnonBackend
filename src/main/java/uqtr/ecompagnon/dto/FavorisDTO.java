package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Resources;
import uqtr.ecompagnon.model.Session;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavorisDTO {

    private long id;
    private long favUser;
    private long favResource;
    private String favDateUpdate;
    private long favStatus;
    private long favSession;


    public FavorisDTO(long favUser, long favResource, String favDateUpdate, long favStatus) {
        this.favUser = favUser;
        this.favResource = favResource;
        this.favDateUpdate = favDateUpdate;
        this.favStatus = favStatus;
    }

    public FavorisDTO(long id, long favUser, long favResource, String favDateUpdate, long favStatus) {
        this.id = id;
        this.favUser = favUser;
        this.favResource = favResource;
        this.favDateUpdate = favDateUpdate;
        this.favStatus = favStatus;
    }
}
