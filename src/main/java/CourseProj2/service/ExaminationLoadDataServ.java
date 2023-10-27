package CourseProj2.service;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

@Configuration
@NoArgsConstructor
public class ExaminationLoadDataServ {
    @Autowired
    private ExaminationRepository repo;

    @Bean
    public ExaminationRepository getExaminationRepository() {
        return repo;
    }

    private Examination initExamination(){
        return Examination.builder()
                .id(null)
                .build();
    }

    @Bean
    public void loadDataIntoRepository() {
        final var path = "data/data.txt";
        final var strPattern = "--exam\\s*(\\w+)" +
                "\\s*--question\\s*([^\\w]+)" +
                "\\s*--answer([^\\w]+)";
        final var pattern = Pattern.compile(strPattern);

        try (Scanner scanner = new Scanner(new File(path))) {
            repo.deleteAll();

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

                    repo.save(examination);

                } else {
                    //TODO: вставить исключение : ошибка считывания файла данных
                    System.out.println("not find");
                }
            }

            System.out.println("загружено " + repo.count() + " записей");

        } catch (Exception ex) {
            //TODO: использовать RunitemException
            System.out.println("err: " + ex.getMessage());
        }

    }

}
