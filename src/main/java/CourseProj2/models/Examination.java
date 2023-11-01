package CourseProj2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Examination {
    @Id
    private String id;

    private String question;
    private String answer;
    private String exam;

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}
