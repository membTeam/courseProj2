package CourseProj2.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Examination implements GeneratedId {
    @Id
    private String id;
    private String question;
    private String answer;
    private String exam;

}
