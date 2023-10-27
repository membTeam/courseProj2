package CourseProj2;


import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import CourseProj2.service.QuestionServiceEmpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: Этот класс вместо ExaminationTests который надо удалить

@SpringBootTest
public class QuestionServiceEmplTests {

    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private QuestionServiceEmpl questionServiceEmpl;

    @Test
    public void canSaveByStringData() {

        var resSave = questionServiceEmpl.add("AnyQuestion", "AnyAnswer", "java");
        assertThat(resSave).isNotNull();

        var id = resSave.getId();
        var findExamination = examinationRepository.findById(id).get();

        assertThat(findExamination).isNotNull();
    }

    @Test
    public void canSaveByExamination() {
        var examination = Examination.builder()
                .question("anyQuestion")
                .answer("someSnswer")
                .exam("java").build();

        var resSave = questionServiceEmpl.add(examination);
        assertThat(resSave).isNotNull();

        var id = resSave.getId();
        var findExamination = examinationRepository.findById(id).get();

        assertThat(findExamination).isNotNull();
    }

    @Test
    public void canRemoveExamination() {
        var firstExamination = examinationRepository.firstExamination("java");

        var resDelete = questionServiceEmpl.remove(firstExamination);

        var findExamination = examinationRepository.findById(resDelete.getId());

        assertThat(findExamination.isEmpty()).isTrue();

    }

    @Test
    public void canGetAllTest() {
        var examination1 = questionServiceEmpl.getAll("java", 2);
        assertThat(examination1.size()).isEqualTo(2);
    }

    @Test
    public void canGetFullDataTest() {
        var examination1 = questionServiceEmpl.getAll("java");
        var amount = examinationRepository.findAllByExam("java");

        assertThat(examination1.size()).isEqualTo(amount);
    }

    @Test
    public void getRandomExaminationTest() {
        var examination1 = questionServiceEmpl.getRandomExamination("java");

        var examination2 = questionServiceEmpl.getRandomExamination("java");

        assertThat(examination1.getId()).isNotEqualTo(examination2.getId());

    }

}
