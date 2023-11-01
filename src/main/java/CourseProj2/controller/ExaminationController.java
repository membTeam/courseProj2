package CourseProj2.controller;

import CourseProj2.models.Examination;
import CourseProj2.service.ExaminationControllerServiceEmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam", produces="application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class ExaminationController {
    @Autowired
    private ExaminationControllerServiceEmpl examControllerServEmpl;

    @PostMapping(path = "/{exam}/add", consumes="application/json")
    public Examination addExamination(@RequestBody Examination examination) {
        return examControllerServEmpl.add(examination);
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id) {
        return examControllerServEmpl.remove(id);
    }

    @GetMapping("/get/{exam}/{amount}")
    public Collection<Examination> getAll(@PathVariable("exam") String exam,
                                          @PathVariable("amount") Integer amount){
        return examControllerServEmpl.getAll(exam, amount);
    }

    @GetMapping("/{exam}/amount")
    public Integer getAllAmount(@PathVariable("exam") String exam){
        return examControllerServEmpl.getAllAmount(exam);
    }

    @GetMapping("/get/{id}")
    public Examination getExaminationById(@PathVariable("id") String id ) {
        return examControllerServEmpl.getExaminationById(id);
    }

}
