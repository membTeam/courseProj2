package CourseProj2.service;

import CourseProj2.exeption.ErrReadFromFileException;
import CourseProj2.models.Examination;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class LoadDataFromFile {

    public List<Examination> dataFromFile() {
        List<Examination> resultList = new ArrayList<>();

        final var path = "data/data.txt";
        final var strPattern = "--exam\\s+(\\w+)" +
                "\\s+--question\\s+(.+)" +
                "\\s+--answer(.+)";
        final var pattern = Pattern.compile(strPattern);

        try (Scanner scanner = new Scanner(new File(path))) {

            while (scanner.hasNextLine()) {
                var strLine = scanner.nextLine();
                var matcher = pattern.matcher(strLine);

                if (matcher.find()) {
                    var exam = matcher.group(1).trim();
                    var question = matcher.group(2).trim();
                    var answer = matcher.group(3).trim();

                    var examination = new Examination();
                    examination.setId(null);
                    examination.setExam(exam);
                    examination.setQuestion(question);
                    examination.setAnswer(answer);

                    resultList.add(examination);
                } else {
                    throw new ErrReadFromFileException("Ошибка преобразования исходных данных");
                }
            }
        } catch (IOException ex) {
            throw new ErrReadFromFileException("Ошибка считывания данных из файла");
        }

        return resultList;
    }

}
