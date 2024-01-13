package CourseProj2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrRequestException extends RuntimeException {
    public ErrRequestException(String err){
        super(err);
    }
}
