package testapp.core;

import java.util.List;

public interface StudentRepository {

    List<Student> findStudents();

    void register(Student student);

    void delete(Integer studentId);
}
