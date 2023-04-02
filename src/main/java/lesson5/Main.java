package lesson5;

import lesson5.dao.StudentDao;
import lesson5.entities.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = SessionFactoryUtils.getSession()) {
            StudentDao dao = new StudentDao(sessionFactory);
            dao.addStudents();
            dao.add(Student.builder()
                    .name("Student x")
                    .mark(9)
                    .build());

            dao.deleteById(1L);
            dao.findById(133L);
            dao.findAll().forEach(System.out::println);

        }
    }
}