package testapp.core;

import java.util.Collections;
import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class StudentDto {
  private Integer id;
  private String name;
  private List<Integer> subjects;

  public StudentDto(Integer id, String name, List<Integer> subjects) {
    this.id = id;
    this.name = name;
    this.subjects = subjects;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Integer> getSubjects() {
    return subjects;
  }

  public Student toStudent(){
    return new Student(id,name,subjects);
  }

  public static StudentDto fromStudent(Student student){
    return new StudentDto(student.getId(),student.getName(),student.getSubjects());
  }

  public static List<StudentDto> fromStudents(List<Student> students){
    List<StudentDto> studentDtoList= Collections.emptyList();
    for (Student student : students) {
      studentDtoList.add(fromStudent(student));
    }
    return studentDtoList;
  }
}
