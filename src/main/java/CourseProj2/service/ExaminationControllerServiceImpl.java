package CourseProj2.service;

import CourseProj2.exeption.ErrItemIsExistsExeption;
import CourseProj2.exeption.ErrRequestException;
import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ExaminationControllerServiceImpl implements ExaminationControllerService {

    private static Set<String> hashSetExam = List.of("java", "math")
            .stream().collect(Collectors.toSet());

    private static void isExistsExam(String exam) {
        if (!hashSetExam.contains(exam)) {
            throw new ErrRequestException("Нет данных по " + exam);
        }
    }

    @Autowired
    private ExaminationRepository repo;

    @Override
    public Examination getExaminationById(String id) {
        var examination = repo.findById(id);

        return examination.orElseThrow( () -> { throw  new ErrRequestException("Нет данных"); } );
    }

    @Override
    public Examination add(Examination examination) {
        if (repo.existsExaminationQuestion(examination.getQuestion())) {
            throw new ErrItemIsExistsExeption("Повторный ввод вопроса");
        }

        return repo.save(examination);
    }

    @Override
    public String remove(String id) {

        var examination = repo.findById(id)
                .orElseThrow(()-> {throw new ErrRequestException("Нет данных");});

        repo.delete(examination);
        return "remove examination" ;
    }

    @Override
    public Collection<Examination> getAll(String exam, int amount) {

        isExistsExam(exam);

        var count = repo.countByExam(exam);
        if (amount > count) {
            throw new ErrRequestException("Количество не должно превышать " + count);
        }

        return repo.findAllByExam(exam, amount);
    }

    @Override
    public Collection<Examination> getAll(String exam) {
        isExistsExam(exam);

        return repo.findAllByExam(exam);
    }

    @Override
    public Iterable<Examination> getRandomExamination(String exam, Integer amount) {

        isExistsExam(exam);

        if (repo.countByExam(exam) < amount) {
            throw new ErrRequestException("Превышен лимит доступного количества");
        }

        return repo.getRandomExamination(exam, amount);
    }

    @Override
    public Examination findExamination(String id) {
        repo.findById(id).orElseThrow(()-> {throw new ErrItemIsExistsExeption("Нет данных");}) ;
    }

    public Integer getAllAmount(String exam){
        isExistsExam(exam);

        return repo.countByExam(exam);
    }
}
