package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resources {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "resources_generator")
    @SequenceGenerator(name = "resources_generator", sequenceName = "resources_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "ressSession", referencedColumnName = "id")
    private Session ressSession;

    @Column(name = "ressDesc")
    private String ressDesc;

    @Column(name = "ressUrl")
    private String ressUrl;
    @ManyToOne()
    @JoinColumn(name = "ressType", referencedColumnName = "id")
    private TypeRessource ressType;

    @Column(name = "ressLieu")
    private String ressLieu;

    @Column(name = "ressDate")
    private Date ressDate;

    @Column(name = "ressVille")
    private String ressVille;

    @Column(name = "ressCodeP")
    private String ressCodeP;

    @ManyToOne()
    @JoinColumn(name = "ressRegion", referencedColumnName = "id")
    private Region ressRegion;

    @Column(name = "ressPicture")
    private Long  ressPicture;

    @Column(name = "ressUpdateDate")
    private Date ressUpdateDate;

    @Column(name = "ressStatus")
    private String ressStatus;

}
