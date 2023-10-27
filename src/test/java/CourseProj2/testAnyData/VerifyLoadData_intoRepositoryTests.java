package CourseProj2.testAnyData;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;


@SpringBootTest
public class VerifyLoadData_intoRepositoryTests {

    @Autowired
    private ExaminationRepository repo;

    private Examination initExamination(){
        return Examination.builder()
                .id(null)
                .build();
    }

    private final String path = "data/data.txt";
    private final String strPattern = "--exam\\s*(\\w+)" +
            "\\s*--question\\s*([^\\w]+)" +
            "\\s*--answer([^\\w]+)";

    private final Pattern pattern = Pattern.compile(strPattern);

    @Test
    public void loadData_fromFile_dataTXT() {
        int num = 0;

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                var strLine = scanner.nextLine();
                var matcher = pattern.matcher(strLine);

                var examination = initExamination();
                if (matcher.find()) {
                    var exam = matcher.group(1);
                    var question = matcher.group(2);
                    var answer = matcher.group(3);

                    examination.setExam(exam);
                    examination.setQuestion(question);
                    examination.setAnswer(answer);

                    var resSave = repo.save(examination);
                    num++;

                } else {
                    System.out.println("not find");
                }
            }

            var numFromRepo = repo.count();
            assertThat(numFromRepo).isEqualTo(num);

            System.out.println("Вставлено: " + num + " rows into database");

        } catch (FileNotFoundException ex) {
            System.out.println("err: " + ex.getMessage());
        }

    }

}
