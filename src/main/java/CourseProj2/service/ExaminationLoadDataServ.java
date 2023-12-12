package CourseProj2.service;

import CourseProj2.exeption.ErrLoadDataIntoRepositoryException;
import CourseProj2.repository.ExaminationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExaminationLoadDataServ implements CommandLineRunner {
    private ExaminationRepository repo;
    private LoadDataFromFile loadDataFromFile;

    public ExaminationLoadDataServ(ExaminationRepository repo, LoadDataFromFile loadDataFromFile) {
        this.repo = repo;
        this.loadDataFromFile = loadDataFromFile;
    }

    @Override
    public void run(String... args) {

        var listExamination = loadDataFromFile.dataFromFile();

        try {
            for (var item : listExamination) {
                repo.save(item);
            }

            System.out.println("Загружены данные в репозиторий");

        } catch (Exception ex) {
            throw new ErrLoadDataIntoRepositoryException("Ошибка заполнения репозитория");
        }
    }
}
