package uqtr.ecompagnon.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplementDTO {
    private Long id;
    private Long questionnaire;
    private Long activite;
    private LocalDateTime endDate;
    private  Long Status;
}
