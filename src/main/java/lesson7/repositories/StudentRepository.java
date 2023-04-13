package lesson7.repositories;

import lesson7.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select S from Student s where s.name = ?1")
    Optional<Student> findByName(String name);
}
