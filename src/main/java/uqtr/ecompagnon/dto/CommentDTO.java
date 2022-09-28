package uqtr.ecompagnon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class CommentDTO {

   private long commentUser;
   private long commentRessource;
   private String commentDate;
   private String commentDesc;
   private long commentStatut;
}
