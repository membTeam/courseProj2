package CourseProj2.service;

import CourseProj2.models.Examination;

import java.util.Collection;

public interface QuestionService {
    Examination add(String question, String answer, String exam);
    Examination add(Examination examination);
    Examination remove(Examination examination);
    Collection<Examination> getAll(String exam, int amount);
    Collection<Examination> getAll(String exam);
    Examination getRandomExamination(String exam);

    int amount(String exam);
    Examination findExamination(String id);
}
