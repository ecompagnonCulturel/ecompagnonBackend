package uqtr.ecompagnon.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UploadResponseDTO {
    private Long succes;
    private List<String> errors;
    private Long total;
    private Long doublon;
}
