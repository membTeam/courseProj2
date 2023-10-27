package CourseProj2.testAnyData;

import CourseProj2.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import CourseProj2.models.Examination;

@SpringBootTest
public class CreateNewExaminationTests {

    @Autowired
    private ExaminationRepository examinationRepository;

    private Examination initExamination(){
        return Examination.builder()
                .id(null)
                .exam("java")
                .question("Simple question")
                .answer("Anser for simple question")
                .build();
    }

    @Test
    public void canCreateNewInstance_Examination() {
        var examination = initExamination();

        var resSave = examinationRepository.save(examination);

        assertThat(resSave).isNotNull();
        assertThat(resSave.getClass().toString()).isEqualTo(Examination.class.toString());

        System.out.println(resSave);

    }

}
