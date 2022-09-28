package uqtr.ecompagnon.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {AppRequestException.class})
    public ResponseEntity<Object> handleAppRequestException(AppRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        //Création d'une entité exception
        AppException appException = new AppException(e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))

        );
        return new ResponseEntity(appException, badRequest);

    }
}
