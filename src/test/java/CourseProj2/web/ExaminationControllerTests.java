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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void testMethod_amount() throws Exception{

        var examination = Examination.builder()
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
                        .jsonPath("$.answer").value("My answer"))
                .andDo(print());
    }

    @Test
    public void getAll_test() throws Exception{

        examinationLoadDataServ.run();

        mvc.perform(get("/exam/get/java/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect( MockMvcResultMatchers
                        .jsonPath("$.length()").value(2));
    }

    @Test
    public void removeItem() throws Exception{
        var id = repo.firstExamination("java").getId();

        var url = "/exam/remove/" + id;
        mvc.perform(get(url))
                .andExpect( MockMvcResultMatchers.status().isOk())
                .andExpect( MockMvcResultMatchers.content().string("remove examination"));
    }

}
