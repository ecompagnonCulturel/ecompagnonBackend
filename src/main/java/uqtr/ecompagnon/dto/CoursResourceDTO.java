package uqtr.ecompagnon.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uqtr.ecompagnon.model.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CoursResourceDTO {
    private long id;
    private String coursResIntention;
    private long coursResResource;
    private long coursResSession;
    private long coursResProf;
    private long coursResCours;
    private long coursResStatus;

}
