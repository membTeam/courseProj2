package CourseProj2.service;

import CourseProj2.exeption.ErrItemIsExistsExeption;
import CourseProj2.exeption.ErrRequestException;
import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class ExaminationControllerServiceEmpl implements ExaminationControllerService {

    @Autowired
    private ExaminationRepository repo;

    @Override
    public Examination getExaminationById(String id) {
        var examination = repo.findById(id);

        if (examination.isEmpty()) {
            throw new ErrRequestException("Нет данных");
        }

        return examination.get();
    }

    @Override
    public int amount(String exam) {
        return repo.countByExam(exam);
    }

    @Override
    public Examination add(Examination examination) {
        if (repo.existsExaminationQuestion(examination.getQuestion())) {
            throw new ErrItemIsExistsExeption("Повторный ввод вопроса");
        }

        repo.save(examination);

        return examination;
    }

    @Override
    public String remove(String id) {

        var examination = repo.findById(id);
        if (examination.isEmpty()) {
            throw new ErrRequestException("Нет данных");
        }

        repo.delete(examination.get());
        return "remove examination" ;
    }

    @Override
    public Collection<Examination> getAll(String exam, int amount) {

        var count = repo.countByExam(exam);
        if (amount > count) {
            throw new ErrRequestException("Количество не должно превышать " + count);
        }

        return repo.findAllByExam(exam, amount);
    }

    @Override
    public Collection<Examination> getAll(String exam) {
        return repo.findAllByExam(exam);
    }

    @Override
    public Examination getRandomExamination(String exam) {
        return repo.getRandomExamination(exam);
    }

    @Override
    public Examination findExamination(String id) {
        var resFind = repo.findById(id);
        return resFind.isEmpty() ? null : resFind.get();
    }

    public Integer getAllAmount(String exam){
        return repo.countByExam(exam);
    }
}
