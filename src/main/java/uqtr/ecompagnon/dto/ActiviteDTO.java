package uqtr.ecompagnon.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ActiviteDTO {
    private Long id;
    private Long session;
    private String  actDesc;



}
