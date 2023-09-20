package org.example;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void tearDown() {
        sessionFactory.close();
    }

    @Test
    public void createTest() {
        System.out.println("Test: create Student");
        Student student1 = new Student("Kristina", "kristina@mail.com");
        Student student2 = new Student("Oleg", "oleg@mail.com");
        Student student3 = new Student("Kri", "kg@mail.com");
        Student student4 = new Student("Oleg", "oleg@mail.com");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void delete() {
        createTest();
        System.out.println("Test: delete Student");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long testId = 3L;
        Student student = (Student) session.get(Student.class, testId);
        session.delete(student);
        session.getTransaction().commit();
        Student student1 = (Student) session.get(Student.class, testId);
        session.close();
        assertNull(student1);

    }

    @Test
    public void update() {
        createTest();
        System.out.println("test update Student");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long testId = 2L;
        Student student = (Student) session.get(Student.class, testId);

        student.setName("Kris");
        student.setEmail("kris@email");
        session.update(student);
        session.getTransaction().commit();
        System.out.println("Update Student");
        Student student1 = (Student) session.get(Student.class, testId);
        session.close();
        assertNotNull(student1);
        assertEquals("Kris", student1.getName());
        assertEquals("kris@email", student1.getEmail());
    }

    @Test
    public void getAll() {
        createTest();
        System.out.println("Test: get all Student");
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Student");
        List<Student> studentList = (List<Student>) query.list();
        System.out.println("List<Student> not null, size = " + studentList.size());
        assertNotEquals(0, studentList.size());

    }

    @Test
    public void get() {
        createTest();
        System.out.println("Test: get Student for id");
        Session session = sessionFactory.openSession();
        Long id = 2L;
        Student student = (Student) session.get(Student.class, id);
        System.out.println("Record from DB is " + student);
        assertEquals(id, student.getId());

    }


}
