package CourseProj2;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import CourseProj2.service.EExam;
import CourseProj2.service.ResponsModelList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExaminationTests {

    @Autowired
    private ExaminationRepository examinationRepository;

    @BeforeEach
    void setup() {
        examinationRepository.deleteAll();
    }

    @Test
    public void canSaveExaminationFindAll() {
        var exam = EExam.JAVA.getStr();

        var examination = Examination.builder().
                question("First questioh").
                answer("Answer for question").exam(exam).
                build();
        examinationRepository.save(examination);

        var res = examinationRepository.findAllByExam("java");

        assertThat(res).asList();

    }

    @Test
    public void canSaveExamination(){
        var exam = EExam.JAVA.getStr();

        var examination = Examination.builder().
                question("First questioh").
                answer("Answer for question").exam(exam).
                build();

        var saveExamination = examinationRepository.save(examination);

        assertThat(saveExamination.getId()).isNotBlank();
        assertThat(saveExamination.getExam()).isEqualTo(saveExamination.getExam());
        assertThat(saveExamination.getQuestion()).isEqualTo(saveExamination.getQuestion());
        assertThat(saveExamination.getAnswer()).isEqualTo(saveExamination.getAnswer());

        assertThat(saveExamination).isEqualTo(examinationRepository.findById(saveExamination.getId()).get());
    }



}
