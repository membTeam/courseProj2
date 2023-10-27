package CourseProj2.testAnyData;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import CourseProj2.models.Examination;


@SpringBootTest
public class GenerateSerialNumberTests {

    private Examination initExamination(){
        return Examination.builder()
                .exam("java")
                .question("Simple question")
                .answer("Anser for simple question")
                .build();
    }

    @Test
    public void examinationGetKeyIndentity() {
        var examination = initExamination();
        var serialNumber = examination.generateSerialNumber(examination);

        assertThat(examination).isNotNull();
        assertThat(serialNumber).isGreaterThan(10000);
        System.out.println("keyIndentityExamination: " + serialNumber);
    }

    @Test
    public void examinationGetKeyIndentity_next() {
        var examination = initExamination();
        var serialNumber = examination.generateSerialNumber(examination);

        assertThat(examination).isNotNull();
        assertThat(serialNumber).isLessThan(20000);
        System.out.println("keyIndentityExamination: " + serialNumber);
    }

}
