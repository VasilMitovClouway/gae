package testapp.adapter.http;


import com.google.sitebricks.client.Transport;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.headless.Request.RequestRead;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Test;
import testapp.adapter.memory.InMemoryStudentRepository;
import testapp.core.Student;
import testapp.core.StudentDto;
import testapp.core.StudentRepository;

import java.net.HttpURLConnection;
import java.util.Collections;

import static org.junit.Assert.*;
import static testapp.adapter.http.Matchers.returns;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class StudentServiceTest {
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void registerStudent() throws Exception {
    StudentRepository studentRepository = new InMemoryStudentRepository();
    StudentService studentService = new StudentService(studentRepository);
    final StudentDto student = new StudentDto(1, "Vasil", Collections.<Integer>emptyList());
    final Request request = context.mock(Request.class);
    context.checking(new Expectations() {{
      oneOf(request).read(StudentDto.class);
      will(returnValue(new RequestRead<StudentDto>() {
        @Override
        public StudentDto as(Class<? extends Transport> transport) {
          return student;
        }
      }));
    }});
    Reply<?> registerReply = studentService.registerStudent(request);
    Reply<?> httpCreatedReply = Reply.saying().status(HttpURLConnection.HTTP_CREATED);
    assertThat(registerReply, returns(httpCreatedReply));
  }

  @Test
  public void studentAlreadyRegistered() throws Exception {
    StudentRepository studentRepository = new InMemoryStudentRepository();
    StudentService studentService = new StudentService(studentRepository);
    studentRepository.register(new Student(1, "Vasil", Collections.<Integer>emptyList()));
    final StudentDto student = new StudentDto(1, "Vasil", Collections.<Integer>emptyList());
    final Request request = context.mock(Request.class);

    context.checking(new Expectations() {{
      oneOf(request).read(StudentDto.class);
      will(returnValue(new RequestRead<StudentDto>() {
        @Override
        public StudentDto as(Class<? extends Transport> transport) {
          return student;
        }
      }));
    }});
    Reply<?> registerReply = studentService.registerStudent(request);

    Reply<?> badRequest = Reply.saying().badRequest();
    assertThat(registerReply, returns(badRequest));
  }
}