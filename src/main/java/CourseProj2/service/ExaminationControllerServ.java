package CourseProj2.service;

import CourseProj2.models.Examination;

import java.util.Collection;

public interface ExaminationControllerServ {
    Examination add(Examination examination);
    String remove(String id);
    Collection<Examination> getAll(String exam, int amount);
    Collection<Examination> getAll(String exam);
    Iterable<Examination> getRandomExamination(String exam, Integer amount);

    Examination getExaminationById(String id);

    //int amount(String exam);
    Examination findExamination(String id);
}
