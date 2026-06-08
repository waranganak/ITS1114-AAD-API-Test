package lk.ijse.AAD.controller;

import lk.ijse.AAD.dto.StudentDTO;
import lk.ijse.AAD.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
         return studentService.saveStudent(studentDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public List<StudentDTO> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/student-details/{id}")
    public StudentDTO getStudentDetails(@PathVariable long id) {
        return studentService.getStudentDetails(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/update")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }


}
