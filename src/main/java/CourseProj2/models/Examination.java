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
    private String keyIndentityExamination;
    private int serialNumber;

    private String question;
    private String answer;
    private String exam;


    public String generateId() {
        return UUID.randomUUID().toString();
    }

    public String gerenateKeyIndentityExamination() {
        return exam + '-' + generateSerialNumber();
    }

    public int generateSerialNumber(){
        if (serialNumber == 0) {
            int min = 10000, max = 20000;

            return (int) (Math.random() * (max-min) + min);

        } else {
            return serialNumber;
        }
    }
}
