package CourseProj2.controller;


import CourseProj2.models.Examination;
import CourseProj2.service.QuestionServiceEmpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam", produces="application/json") // produces="application/json"
@CrossOrigin(origins = "http://localhost:3000")
public class ExaminationController {
    private QuestionServiceEmpl questionServiceEmpl;
    public ExaminationController(QuestionServiceEmpl questionServiceEmpl){
        this.questionServiceEmpl = questionServiceEmpl;
    }

    @GetMapping("/get/{exam}/{amount}")
    public Collection<Examination> getAll(@PathVariable("exam") String exam,
                                          @PathVariable("amount") int amount){
        // TODO: Нужна проверка на возвращаемое кол-во. Использование исключения
        return questionServiceEmpl.getAll(exam, amount);
    }

}
