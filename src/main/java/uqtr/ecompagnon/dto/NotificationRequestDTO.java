package uqtr.ecompagnon.dto;

import lombok.*;

@Data
public class NotificationRequestDTO {
    private String target;
    private String title;
    private String body;
}
