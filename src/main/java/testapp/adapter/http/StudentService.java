package testapp.adapter.http;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.At;
import com.google.sitebricks.client.transport.Json;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Delete;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;
import testapp.core.Student;
import testapp.core.StudentAlreadyExistsException;
import testapp.core.StudentDto;
import testapp.core.StudentRepository;

import java.net.HttpURLConnection;
import java.util.List;

@At("/student")
public class StudentService {
  private StudentRepository repository;

  @Inject
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  @Get
  public Reply<?> getStudents() {
    List<Student> studentList = repository.findStudents();
    return Reply.with(StudentDto.fromStudents(studentList)).as(Json.class);
  }

  @Post
  public Reply<?> registerStudent(Request request) {
    StudentDto studentDto = request.read(StudentDto.class).as(Json.class);
    try {
      repository.register(studentDto.toStudent());
    } catch (StudentAlreadyExistsException e) {
      return Reply.saying().badRequest();
    }
    return Reply.saying().status(HttpURLConnection.HTTP_CREATED);
  }

  @Delete
  @At("/:studentId")
  public Reply<?> deleteStudentById(@Named("studentId") Integer studentId) {
    repository.delete(studentId);
    return Reply.saying().ok();
  }
}
