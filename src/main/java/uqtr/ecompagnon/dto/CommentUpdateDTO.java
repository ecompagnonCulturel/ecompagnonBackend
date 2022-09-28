package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentUpdateDTO {
    private long id;
    private long commentUser;
    private long commentRessource;
    private String commentDate;
    private String commentDesc;
    private long commentStatut;
}
