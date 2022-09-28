package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "connected")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Connected {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appUser", referencedColumnName = "idUsers")
    private AppUser appUser;

    @Column(name = "conDate")
    private LocalDateTime conDate;

    @Column(name = "conDeconDate")
    private LocalDateTime conDeconDate;


}
