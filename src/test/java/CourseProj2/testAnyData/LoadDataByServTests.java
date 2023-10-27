package CourseProj2.testAnyData;


import CourseProj2.service.ExaminationLoadDataServ;
import org.springframework.boot.test.context.SpringBootTest;
import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

@SpringBootTest
public class LoadDataByServTests {

    @Autowired
    private ExaminationLoadDataServ examinationLoadDataServ;

    @Test
    public void CanLoadData_intoRepository() {

        examinationLoadDataServ.loadDataIntoRepository();
        var repo = examinationLoadDataServ.getExaminationRepository();

        assertThat(repo.count()).isEqualTo(4);
    }

}
