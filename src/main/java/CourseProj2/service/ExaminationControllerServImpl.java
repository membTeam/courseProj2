package CourseProj2.service;

import CourseProj2.exeption.ErrItemIsNotExistsExeption;
import CourseProj2.exeption.ErrRequestException;
import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class ExaminationControllerServImpl implements ExaminationControllerServ {
    private ExaminationRepository repo;

    public ExaminationControllerServImpl(ExaminationRepository repo) {
        this.repo = repo;
    }

    private static Set<String> hashSetExam =
            Stream.of("java", "math").collect(Collectors.toSet());

    private static void isExistsExam(String exam) {
        if (!hashSetExam.contains(exam)) {
            throw new ErrRequestException("Нет данных по " + exam);
        }
    }

    @Override
    public Examination getExaminationById(String id) {
        return  repo.findById(id).orElseThrow(() -> { throw  new ErrRequestException("Нет данных"); });
    }

    @Override
    public Examination add(Examination examination) {
        if (repo.existsExaminationQuestion(examination.getQuestion())) {
            throw new ErrItemIsNotExistsExeption("Повторный ввод вопроса");
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

        if (amount == 0) {
            throw new ErrRequestException("Кол-во д/быть больше нуля");
        }

        if (repo.countByExam(exam) < amount) {
            throw new ErrRequestException("Превышен лимит доступного количества");
        }

        return repo.getRandomExamination(exam, amount);
    }

    @Override
    public Examination findExamination(String id) {
        return repo.findById(id).orElseThrow(()-> {throw new ErrItemIsNotExistsExeption("Нет данных");}) ;
    }

    public Integer getAllAmount(String exam){
        isExistsExam(exam);

        return repo.countByExam(exam);
    }
}
