package library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionWrapper> notFoundException(final NotFoundException e) {
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionWrapper> runtimeException(final RuntimeException e) {
        log.error("Internal server error: ", e);
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionWrapper> badRequestException(final BadRequestException e) {
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
