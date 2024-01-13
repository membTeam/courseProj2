package CourseProj2.web;

import CourseProj2.models.Examination;
import CourseProj2.repository.ExaminationRepository;
import CourseProj2.service.ExaminationLoadDataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ExaminationControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ExaminationLoadDataConfig examinationLoadDataServ;

    @Autowired
    ExaminationRepository repo;

    @Test
    public void addWithParamMath() throws Exception{
        var examination = new Examination(
                null, "My question", "My answer", "math" );

        mvc.perform(post("/exam/math/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(examination))
                )
                .andExpect( status().isOk())
                .andExpect( MockMvcResultMatchers
                        .jsonPath("$.answer").value("My answer"));

    }

    @Test
    public void addWithParamJava() throws Exception{

        var examination = Examination.builder()
                .id(null)
                .exam("java")
                .answer("My answer")
                .question("Simple question")
                .build();

        mvc.perform(post("/exam/java/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(examination))
                )
                .andExpect( status().isOk())
                .andExpect( MockMvcResultMatchers
                        .jsonPath("$.answer").value("My answer"));
    }

    @Test
    public void removeItem() throws Exception{
        var id = repo.firstExamination("java").getId();

        var url = "/exam/remove/" + id;
        mvc.perform(get(url))
                .andExpect( MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers
                        .content().string("remove examination"));
    }

    @Test
    public void getAllForAmount() throws Exception{

        mvc.perform(get("/exam/get/java/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect( MockMvcResultMatchers
                        .jsonPath("$.length()").value(2));
    }

    @Test
    public void getAll() throws Exception{

        String count = String.valueOf(repo.getCountForExam("java"));

        mvc.perform(get("/exam/java/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.content().string(count));
    }

    @Test
    public void getExaminationById() throws Exception {
        var id = repo.firstExamination("java").getId();

        var url = "/exam/get/"+id;
        mvc.perform(get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect( MockMvcResultMatchers
                        .jsonPath("$.id").value(id));
    }

    @Test
    public void getRandExamAmount() throws Exception {
        mvc.perform(get("/exam/get-rand/java/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(  MockMvcResultMatchers
                        .jsonPath("$.length()").value(2));
    }

    @Test
    public void getRandExamAmountWithException() throws Exception {
        mvc.perform(get("/exam/get-rand/java/10000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
