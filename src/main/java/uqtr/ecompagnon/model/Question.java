package uqtr.ecompagnon.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import uqtr.ecompagnon.dto.JsonStringDTO;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "question")
//@Table(name = "question")
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
public class Question implements Serializable {
    private static final long serialVersionUID = 456778567857L;
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    @Column(name = "id")
    private Long id;
    private String questType;
    private String questCate;
    private Long  questOrdre;
   // private JsonStringDTO questModalite;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String questModalite;
    private  String questTypeRepons;
    private  String questDesc;
    private  String questFilleType;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String questFilleModalite;
    private  String  QuestTypeReponsFille;
    private  String questFilleDesc;
    private  Long    questStatus;


}
