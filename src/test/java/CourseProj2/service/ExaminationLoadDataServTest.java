package CourseProj2.service;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

public class ExaminationLoadDataServTest {

    @Mock
    LoadDataFromFile loadDataFromFile;
    @Mock
    ExaminationRepository repo;
    @InjectMocks
    ExaminationLoadDataServ examinationLoadDataServ;

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

        var examination = new Examination(null, "java",
                "questin1", "answer1");

        List<Examination> listExamination = List.of(examination);

        when(loadDataFromFile.dataFromFile()).thenReturn(listExamination);
        when(repo.save(examination)).thenReturn(examination);

        examinationLoadDataServ.run("");
    }
}
