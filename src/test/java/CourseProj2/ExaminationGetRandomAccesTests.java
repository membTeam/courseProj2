package CourseProj2;

import CourseProj2.repository.ExaminationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ExaminationGetRandomAccesTests {
    @Autowired
    private ExaminationRepository examinationRepository;

    @Test
    public void canGetRandomExamination() {
        var examination1 = examinationRepository.getRandomExamination("java");
        var examination2 = examinationRepository.getRandomExamination("");

        examination2 = examinationRepository.getRandomExamination("java");

        assertThat(examination1.generateId()).isNotEqualTo(examination2.generateId());
    }

    @Test
    public void canGetRandomAmountExamination() {
        var count = examinationRepository.countByExam("java");
        var examination = examinationRepository.getRandomExamination("java", count + 1);

        assertThat(examination.size()).isEqualTo(count);
    }


}
