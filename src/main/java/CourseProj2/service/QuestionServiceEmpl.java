package CourseProj2.service;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class QuestionServiceEmpl implements QuestionService {

    private ExaminationRepository examinationRepository;

    public QuestionServiceEmpl(ExaminationRepository examinationRepository){
        this.examinationRepository = examinationRepository;
    }

    @Override
    public int amount(String exam) {
        return examinationRepository.countByExam(exam);
    }

    @Override
    public Examination add(String question, String answer, String exam) {
        var examination = Examination.builder()
                .question(question)
                .answer(answer)
                .exam(exam).build();

        return add(examination);
    }

    @Override
    public Examination add(Examination examination) {
        return  examinationRepository.save(examination);
    }

    @Override
    public Examination remove(Examination examination) {
        examinationRepository.delete(examination);
        return examination;
    }

    @Override
    public Collection<Examination> getAll(String exam, int amount) {
        return examinationRepository.findAllByExam(exam, amount);
    }

    @Override
    public Collection<Examination> getAll(String exam) {
        return examinationRepository.findAllByExam(exam);
    }

    @Override
    public Examination getRandomExamination(String exam) {
        return examinationRepository.getRandomExamination(exam);
    }

    @Override
    public Examination findExamination(String id) {
        var resFind = examinationRepository.findById(id);
        return resFind.isEmpty() ? null : resFind.get();
    }
}
