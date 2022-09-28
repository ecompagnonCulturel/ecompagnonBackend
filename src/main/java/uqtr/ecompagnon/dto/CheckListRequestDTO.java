package uqtr.ecompagnon.dto;

import lombok.*;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CheckListRequestDTO {
    private ArrayList<Long> idEtud;
    private Long group;
}
