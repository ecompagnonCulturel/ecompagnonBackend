package uqtr.ecompagnon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "region_generator")
    @SequenceGenerator(name = "region_generator", sequenceName = "region_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;
    @Column(name = "regNom")
    private String regNom;

   /* @OneToMany(targetEntity=Resources.class, mappedBy="ressRegion",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resources> resources = new ArrayList<>();*/
}
