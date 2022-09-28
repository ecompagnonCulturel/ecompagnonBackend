package uqtr.ecompagnon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uqtr.ecompagnon.dto.JsonStringDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "reponse")
//@Table(name = "reponse")
public class Reponse {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "reponse_generator")
    @SequenceGenerator(name = "reponse_generator", sequenceName = "reponse_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "question", referencedColumnName = "id")
    private Question question;
    @ManyToOne()
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;
    @ManyToOne()
    @JoinColumn(name = "cp", referencedColumnName = "idUsers")
    private AppUser cp;
   /* @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;*/
    private String questType;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String reponsModalite;
    private String reponsType;
    private String  reponsText;
    private Long reponsEntier;
    private String questFilleType;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String responsFilleModalite;
    private String reponsFilleType;
    private String reponsFilleText;
    private Long   reponsEntierFille;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reponsDate;
    @ManyToOne()
    @JoinColumn(name = "session", referencedColumnName = "id")
    private Session session;

}
