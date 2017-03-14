package testapp.adapter.memory;

import testapp.core.Student;
import testapp.core.StudentAlreadyExistsException;
import testapp.core.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryStudentRepository implements StudentRepository {
  private List<Student> studentList = new ArrayList<>();

  @Override
  public List<Student> findStudents() {
    return studentList;
  }

  @Override
  public void register(Student student) {
    for (Student each : studentList) {
      if (Objects.equals(each.getId(), student.getId())) {
        throw new StudentAlreadyExistsException();
      }
    }
    studentList.add(student);
  }

  @Override
  public void delete(Integer studentId) {
    for (Student each : studentList) {
      if (Objects.equals(each.getId(), studentId)) {
        studentList.remove(each);
        return;
      }
    }
  }
}
