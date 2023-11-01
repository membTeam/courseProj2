package CourseProj2.service;

import CourseProj2.exeption.ErrLoadDataIntoRepositoryException;
import CourseProj2.exeption.ErrReadFromFileException;
import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

@Configuration
public class ExaminationLoadDataServ implements CommandLineRunner {
    @Autowired
    private ExaminationRepository repo;

    public ExaminationRepository getExaminationRepository() {
        return repo;
    }

    private static Examination initExamination(){
        return Examination.builder()
                .id(null)
                .build();
    }

    @Override
    public void run(String... args) {
        try {
            final var path = "data/data.txt";
            final var strPattern = "--exam\\s*(\\w+)" +
                    "\\s*--question\\s*(.+)" +
                    "\\s*--answer(.+)";
            final var pattern = Pattern.compile(strPattern);

            try (Scanner scanner = new Scanner(new File(path))) {
                repo.deleteAll();

                while (scanner.hasNextLine()) {
                    var strLine = scanner.nextLine();
                    var matcher = pattern.matcher(strLine);

                    var examination = initExamination();
                    if (matcher.find()) {
                        var exam = matcher.group(1).trim();
                        var question = matcher.group(2).trim();
                        var answer = matcher.group(3).trim();

                        examination.setExam(exam);
                        examination.setQuestion(question);
                        examination.setAnswer(answer);

                        repo.save(examination);

                    } else {
                        System.out.println("err: Ошибка считывания строки");
                        throw new ErrReadFromFileException("no find item");
                    }
                }
            }

            System.out.println("загружено " + repo.count() + " записей");

        } catch (Exception ex) {
            System.out.println("err: " + ex.getMessage());
            throw new ErrLoadDataIntoRepositoryException("Нет начальных данных в БД");
        }
    }
}
