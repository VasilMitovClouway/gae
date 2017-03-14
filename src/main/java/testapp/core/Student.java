package testapp.core;

import com.google.appengine.api.datastore.Entity;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Student {
  private Integer id;
  private String name;
  private List<Integer> subjects;

  public Student() {
  }

  public Student(Integer id, String name, List<Integer> subjects) {
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

  public Entity toEntity(String kind){
    Entity studentEntity=new Entity(kind,id);
    studentEntity.setProperty("id",this.id);
    studentEntity.setProperty("name",this.name);
    studentEntity.setProperty("subjects",this.subjects);
    return studentEntity;
  }

  public Student fromEntity(Entity entity){
    Integer id= (Integer) entity.getProperty("id");
    String name=(String) entity.getProperty("name");
    List<Integer> subjects=(List<Integer>)entity.getProperty("subjects");
    return new Student(id,name,subjects);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    if (id != null ? !id.equals(student.id) : student.id != null) return false;
    if (name != null ? !name.equals(student.name) : student.name != null) return false;
    return subjects != null ? subjects.equals(student.subjects) : student.subjects == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
    return result;
  }
}
