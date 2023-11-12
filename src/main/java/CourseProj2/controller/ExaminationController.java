package CourseProj2.controller;

import CourseProj2.models.Examination;
import CourseProj2.service.ExaminationControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/exam", produces="application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class ExaminationController {
    @Autowired
    private ExaminationControllerServiceImpl controllerServ;

    @PostMapping(path = "/{exam}/add", consumes="application/json")
    public Examination addExamination(@RequestBody Examination examination) {
        return controllerServ.add(examination);
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id) {
        return controllerServ.remove(id);
    }

    @GetMapping("/get/{exam}/{amount}")
    public Collection<Examination> getAll(@PathVariable("exam") String exam,
                                          @PathVariable("amount") Integer amount){
        return controllerServ.getAll(exam, amount);
    }

    @GetMapping("/{exam}/amount")
    public Integer getAllAmount(@PathVariable("exam") String exam){
        return controllerServ.getAllAmount(exam);
    }

    @GetMapping("/get/{id}")
    public Examination getExaminationById(@PathVariable("id") String id ) {
        return controllerServ.getExaminationById(id);
    }

    @GetMapping("/get-rand/{exam}/{amount}")
    public Iterable<Examination> getRandExamAmount(@PathVariable("exam") String exam,
                                                  @PathVariable("amount") Integer amount ) {

        return amount > 1
            ? controllerServ.getRandomExamination(exam, --amount)
            : controllerServ.getRandomExamination(exam, amount);
    }

}
