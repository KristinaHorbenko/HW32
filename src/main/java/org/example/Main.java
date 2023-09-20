package org.example;

public class Main {
    public static void main(String[] args) {
        StudentService student = new StudentDao();
        Student student1 = new Student("Kristina", "kristina@mail.com");
        Student student2 = new Student("Oleg", "oleg@mail.com");
        Student student3 = new Student("Kri", "kg@mail.com");
        Student student4 = new Student("Oleg", "oleg@mail.com");


        student.create(student1);
        student.create(student2);
        student.create(student3);
        student.create(student4);

        student.getAll().forEach(System.out::println);

        student2.setName("Kris");
        student2.setEmail("email@Email.com");

        System.out.println(student.update(student2));

        student.get(1L);

        student.delete(student4);
    }

}