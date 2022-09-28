package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "note")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "note_generator")
    @SequenceGenerator(name = "note_generator", sequenceName = "note_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "noteUser", referencedColumnName = "idUsers")
    private AppUser noteUser;


    @ManyToOne()
    @JoinColumn(name = "noteResource", referencedColumnName = "id")
    private Resources noteResource;

    @Column(name = "noteDate")
    private LocalDateTime noteDate;

    @Column(name = "noteDesc")
    private String noteDesc;

    @Column(name = "noteStatus")
    private long noteStatus;


}
