package CourseProj2.testAnyData;


import CourseProj2.service.ExaminationLoadDataServ;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
