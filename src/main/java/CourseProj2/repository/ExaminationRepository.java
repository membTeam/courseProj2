package CourseProj2.repository;

import CourseProj2.models.Examination;
import CourseProj2.service.EExam;
import CourseProj2.service.ResponsModel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ExaminationRepository extends CrudRepository<Examination, String> {
    @Query("select * from examination e where e.exam = :exam")
    Collection<Examination> findAllByExam(@Param("exam") String exam);

    @Query("select * from examination e where e.exam = :exam limit :amount")
    Collection<Examination> findAllByExam(@Param("exam") String  exam,
                                     @Param("amount") int amount);

    @Query("select count(*) amount from examination e where e.exam = :exam")
    int countByExam(@Param("exam") String exam);

    @Query("select * from examination where exam = :exam order by random() limit 1")
    Examination getRandomExamination(@Param("exam") String exam );

    @Query("select * from examination where exam = :exam order by random() limit :amount")
    Collection<Examination> getRandomExamination(@Param("exam") String exam, @Param("amount") int amount);

    @Query("select * from examination where exam = :exam limit 1")
    Examination firstExamination(@Param("exam") String exam);

}
