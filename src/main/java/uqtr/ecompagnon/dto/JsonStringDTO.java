package uqtr.ecompagnon.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class JsonStringDTO implements Serializable {
   // private static final long serialVersionUID = 456778567857L;
    private Long id;
    private String value;
}
