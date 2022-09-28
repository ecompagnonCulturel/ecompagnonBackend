package uqtr.ecompagnon.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;
@Getter
public class AppException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;



    public AppException(String message,
                        HttpStatus httpStatus,
                        ZonedDateTime zonedDateTime) {
        this.message = message;
       this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }
}
