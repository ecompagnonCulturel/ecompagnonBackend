package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uqtr.ecompagnon.model.Question;

import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDuplicDTO {
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
    private Long ordre;
}
