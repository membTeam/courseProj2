package CourseProj2.controller;

import CourseProj2.models.Examination;
import CourseProj2.service.ExaminationControllerServImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam", produces="application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class ExaminationController {
    private ExaminationControllerServImpl controllerServ;

    public ExaminationController(ExaminationControllerServImpl controllerServ) {
        this.controllerServ = controllerServ;
    }

    @PostMapping(path = "/{exam}/add", consumes="application/json")
    public Examination addExamination(@RequestBody Examination examination) {
        return controllerServ.add(examination);
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id) {
        return controllerServ.remove(id);
    }

    @GetMapping("/get/{exam}/{amount}")
    public Collection<Examination> getAllForAmount(@PathVariable("exam") String exam,
                                          @PathVariable("amount") Integer amount){
        return controllerServ.getAll(exam, amount);
    }

    @GetMapping("/{exam}/")
    public Integer getAll(@PathVariable("exam") String exam){
        return controllerServ.getAllAmount(exam);
    }

    @GetMapping("/get/{id}")
    public Examination getExaminationById(@PathVariable("id") String id ) {
        return controllerServ.getExaminationById(id);
    }

    @GetMapping("/get-rand/{exam}/{amount}")
    public Iterable<Examination> getRandExamAmount(@PathVariable("exam") String exam,
                                                   @PathVariable("amount") Integer amount ) {
        return controllerServ.getRandomExamination(exam, amount);
    }

}

