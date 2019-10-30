package hybridlibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionWrapper> notFoundException(final NotFoundException e) {
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppRuntimeException.class)
    public ResponseEntity<ExceptionWrapper> runtimeException(final RuntimeException e) {
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionWrapper> badRequestException(final BadRequestException e) {
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
