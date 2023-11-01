package CourseProj2.service;

import CourseProj2.models.Examination;

import java.util.Collection;

public interface ExaminationControllerService {
    Examination add(Examination examination);
    String remove(String id);
    Collection<Examination> getAll(String exam, int amount);
    Collection<Examination> getAll(String exam);
    Examination getRandomExamination(String exam);

    Examination getExaminationById(String id);

    int amount(String exam);
    Examination findExamination(String id);
}
