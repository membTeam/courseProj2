package CourseProj2;


import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import CourseProj2.service.ExaminationControllerServiceEmpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: Этот класс вместо ExaminationTests который надо удалить

@SpringBootTest
public class ExaminationControllerServiceEmplTests {

    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private ExaminationControllerServiceEmpl questionServiceEmpl;

    @Test
    public void canSaveByStringData() {

        var emploee = Examination.builder().question("AnyQuestion").answer("AnyAnswer").exam("java").build();

        var resSave = questionServiceEmpl.add(emploee);
        assertThat(resSave).isNotNull();

        var id = resSave.getId();
        var findExamination = examinationRepository.findById(id).get();

        assertThat(findExamination).isNotNull();
    }

    @Test
    public void canSaveByExamination() {
        var examination = Examination.builder()
                .question("anyQuestion2")
                .answer("someSnswer2")
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

        var resDelete = questionServiceEmpl.remove(firstExamination.getId());

        assertThat(resDelete).isEqualTo("remove examination");

    }

    @Test
    public void canGetAllTest() {
        var examination1 = questionServiceEmpl.getAll("java", 2);
        assertThat(examination1.size()).isEqualTo(2);
    }

    @Test
    public void canGetFullDataTest() {
        var amount = questionServiceEmpl.getAll("java").size();
        var count = examinationRepository.findAllByExam("java").size();

        assertThat(amount).isEqualTo(count);
    }

    @Test
    public void getRandomExaminationTest() {
        var examination1 = questionServiceEmpl.getRandomExamination("java");

        var examination2 = questionServiceEmpl.getRandomExamination("java");

        assertThat(examination1.generateId()).isNotEqualTo(examination2.generateId());

    }

}
