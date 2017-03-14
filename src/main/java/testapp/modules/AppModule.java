package testapp.modules;

import com.google.inject.Scopes;
import com.google.sitebricks.SitebricksModule;
import testapp.adapter.http.StudentService;
import testapp.adapter.memory.InMemoryStudentRepository;
import testapp.core.StudentRepository;

public class AppModule extends SitebricksModule {

  @Override
  protected void configureSitebricks() {
    bind(StudentRepository.class).to(InMemoryStudentRepository.class).in(Scopes.SINGLETON);
    at("/student").serve(StudentService.class);
  }
}
