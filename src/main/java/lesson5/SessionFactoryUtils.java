package lesson5;

import lesson5.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {

    private final SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}