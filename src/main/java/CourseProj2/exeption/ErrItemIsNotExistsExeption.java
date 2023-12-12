package CourseProj2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ErrItemIsNotExistsExeption extends RuntimeException {
    public ErrItemIsNotExistsExeption(String err) {
        super(err);
    }
}
