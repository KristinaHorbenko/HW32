package org.example;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDao implements StudentService{
    @Override
    public Long create(Student student) {

        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long idLong = (Long) session.save(student);
        session.getTransaction().commit();
        return idLong;
    }

    @Override
    public void delete(Student student) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
    }

    @Override
    public Student update(Student student) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        Long idUpdate = student.getId();
        return get(idUpdate);
    }

    @Override
    public List<Student> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student" );
        return (List<Student>) query.list();
    }

    @Override
    public Student get(Long id) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Session session = sessionFactory.openSession();
        return (Student) session.get(Student.class, id);
    }


}
