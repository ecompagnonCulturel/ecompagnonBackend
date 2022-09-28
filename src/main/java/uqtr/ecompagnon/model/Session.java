package uqtr.ecompagnon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "session_generator")
    @SequenceGenerator(name = "session_generator", sequenceName = "session_sequence", allocationSize = 1)

    @Column(name = "id")
    private long id;

    @Column(name = "sessNom")
    private String sessNom;

    @Column(name = "sessStatus")
    private long sessStatus;

    @Column(name = "sessYear")
    private long sessYear;

    @Column(name = "sessStart")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate sessStart;

    @Column(name = "sessMiddle")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate sessMiddle;

    @Column(name = "sessEnd")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate sessEnd;

   /* @OneToMany(targetEntity=Resources.class, mappedBy="ressSession",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resources> resources = new ArrayList<>();
*/
}
