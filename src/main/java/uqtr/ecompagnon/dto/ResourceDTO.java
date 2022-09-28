package uqtr.ecompagnon.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ResourceDTO {
   private long id;
   private String ressDesc;
   private long ressSession;
   private String ressUrl;
   private long ressType;
   private long ressRegion;
   private String ressLieu;
   private String ressVille;
   private String ressCodeP;
}
