package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "typeRessource")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeRessource {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "typeRessource_generator")
    @SequenceGenerator(name = "typeRessource_generator", sequenceName = "typeRessource_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "tRDesc")
    private String tRDesc;

    @Column(name = "tRStatus")
    private long tRStatus;

    @Column(name = "tRAvatar")
    private String tRAvatar;

    @Column(name = "typeOrdre")
    private long typeOrdre;

  /*  @OneToMany(targetEntity=Resources.class, mappedBy="ressType",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resources> resources = new ArrayList<>();*/

}
