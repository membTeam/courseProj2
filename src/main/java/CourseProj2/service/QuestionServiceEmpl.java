package CourseProj2.service;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;

import java.util.Collection;


public class QuestionServiceEmpl implements QuestionService {

    private ExaminationRepository repo;

    public void fillData(){

    }

    /*public QuestionServiceEmpl(ExaminationLoadDataServ examinationLoadDataServ){
        examinationLoadDataServ.loadDataIntoRepository();
        this.repo = examinationLoadDataServ.getExaminationRepository();
    }*/

    @Override
    public int amount(String exam) {
        return repo.countByExam(exam);
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
        return  repo.save(examination);
    }

    @Override
    public Examination remove(Examination examination) {
        repo.delete(examination);
        return examination;
    }

    @Override
    public Collection<Examination> getAll(String exam, int amount) {
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
