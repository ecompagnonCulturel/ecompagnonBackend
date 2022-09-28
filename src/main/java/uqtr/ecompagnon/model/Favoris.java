package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "favoris")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favoris {


    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "favoris_generator")
    @SequenceGenerator(name = "favoris_generator", sequenceName = "favoris_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "favUser", referencedColumnName = "idUsers")
    private AppUser favUser;

    @ManyToOne()
    @JoinColumn(name = "favResource", referencedColumnName = "id")
    private Resources favResource;

    @Column(name = "favDateUpdate")
    private LocalDateTime favDateUpdate;

    @Column(name = "favStatus")
    private long favStatus;

    @ManyToOne()
    @JoinColumn(name = "favSession", referencedColumnName = "id")
    private Session favSession;


    public Favoris(long id, AppUser favUser, Resources favResource, LocalDateTime favDateUpdate, long favStatus) {
        this.id = id;
        this.favUser = favUser;
        this.favResource = favResource;
        this.favDateUpdate = favDateUpdate;
        this.favStatus = favStatus;
    }




}
