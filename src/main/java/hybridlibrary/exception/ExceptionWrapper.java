package hybridlibrary.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ExceptionWrapper {

    private final String errorMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private final Date date;

    public ExceptionWrapper(String errorMessage) {
        this.errorMessage = errorMessage;
        this.date = new Date();
    }

}
