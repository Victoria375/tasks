package lesson7.controllers;

import lesson7.entities.Student;
import lesson7.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping
    public Student addOrUpdateStudent(@RequestBody Student student) {
        return studentService.addOrUpdateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/{name}")
    public Student findStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }

}
