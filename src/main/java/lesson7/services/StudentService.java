package lesson7.services;

import lesson7.entities.Student;
import lesson7.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student addOrUpdateStudent(Student student) {
        if (student.getId() != null) {
            Student studentFromDb = getStudentById(student.getId());
            if (student.getName() != null) {
                studentFromDb.setName(student.getName());
            }
            if (student.getAge() != 0) {
                studentFromDb.setAge(student.getAge());
            }
            return studentRepository.save(studentFromDb);
        } else {
            Student newStudent = new Student();
            newStudent.setName(student.getName());
            newStudent.setAge(student.getAge());
            return studentRepository.save(newStudent);
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Student getStudentByName(String name) {
        return studentRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
