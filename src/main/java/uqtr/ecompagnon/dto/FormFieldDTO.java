package uqtr.ecompagnon.dto;

import liquibase.pro.packaged.id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FormFieldDTO {
    private  Long id;
    private  String value;
}
