package CourseProj2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ErrLoadDataIntoRepositoryException extends RuntimeException {
    public ErrLoadDataIntoRepositoryException(String err){
        super(err);
    }
}
