package CourseProj2.service;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExaminationLoadDataServTest {

    @Mock
    LoadDataFromFile dataFromFile;
    @Mock
    ExaminationRepository repo;
    @InjectMocks
    ExaminationLoadDataConfig dataConfig;

    @BeforeEach
    private void setUp() {
        initMocks(this);
    }

    @Test
    public void loadDataFromFile() {

        var loadData = new LoadDataFromFile();

        var resLoad = loadData.dataFromFile();

        assertNotNull(resLoad);
        assertTrue(resLoad.size()>0);
    }

    @Test
    public void ruh() {

        List<Examination> listExamination = List.of(
                new Examination(null, "java", "questin1", "answer1"),
                new Examination(null, "java", "questin2", "answer2")
        );

        when(dataFromFile.dataFromFile()).thenReturn(listExamination);

        dataConfig.run("");
    }
}
