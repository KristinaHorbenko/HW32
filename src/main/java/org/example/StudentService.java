package org.example;

import java.util.List;

public interface StudentService {
    Long create(Student student);
    void delete (Student student);
    Student update (Student student);
    List<Student> getAll();
    Student get(Long id);
}
