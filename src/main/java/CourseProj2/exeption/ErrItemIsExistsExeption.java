package CourseProj2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ErrItemIsExistsExeption extends RuntimeException {
    public ErrItemIsExistsExeption(String err) {
        super(err);
    }
}
