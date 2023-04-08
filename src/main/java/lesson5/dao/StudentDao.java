package lesson5.dao;

import lesson5.SessionFactoryUtils;
import lesson5.entities.Student;
import org.hibernate.Session;

import java.util.List;
import java.util.Random;

public class StudentDao {

    private final SessionFactoryUtils sessionFactoryUtils;

    public StudentDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    Session session = null;

    public Student findById(Long id) {
        session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return student;
    }

    public List<Student> findAll() {
        session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        List<Student> list = session.createQuery("FROM Student").getResultList();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return list;
    }

    public void deleteById(Long id) {
        session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Student s WHERE s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void saveOrUpdate(Student student) {
        session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void add(Student student) {
        session = sessionFactoryUtils.getSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    public void addStudents() {

        Random random = new Random();
        Session session = sessionFactoryUtils.getSession();
        session.beginTransaction();

        for (int i = 1; i <= 1000; i++) {
            String name = "Student " + i;
            double mark = random.nextInt(100) + 1;
            Student student = Student.builder()
                    .name(name)
                    .mark((int) mark)
                    .build();
            session.save(student);
        }
        session.getTransaction().commit();
    }

}