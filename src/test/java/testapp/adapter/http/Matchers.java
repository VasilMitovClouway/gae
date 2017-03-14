package testapp.adapter.http;

import com.google.sitebricks.headless.Reply;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import testapp.core.Student;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Matchers {
  public static Matcher<List<Student>> containsUser(final Student user) {
    return new TypeSafeMatcher<List<Student>>() {
      @Override
      protected boolean matchesSafely(List<Student> item) {
        for (Student each : item) {
          if (each.getId() == user.getId()) {
            return true;
          }
        }
        return false;
      }

      @Override
      protected void describeMismatchSafely(List<Student> item, Description mismatchDescription) {
        mismatchDescription.appendText("value returns ");
        mismatchDescription.appendValue(null);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("contains value ");
        description.appendValue(user);
      }
    };
  }

  public static Matcher<Reply<?>> returns(final Reply<?> reply) {
    return new TypeSafeMatcher<Reply<?>>() {
      @Override
      protected boolean matchesSafely(Reply<?> item) {
        return item.equals(reply);
      }

      @Override
      protected void describeMismatchSafely(Reply<?> item, Description mismatchDescription) {
        mismatchDescription.appendText("returns ");
        mismatchDescription.appendValue(item);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("expect to be ");
        description.appendValue(reply);
      }
    };
  }

  public static Matcher<Student> sameAs(final Student user) {
    return new TypeSafeMatcher<Student>() {
      @Override
      protected boolean matchesSafely(Student item) {
        return user.getId() == item.getId();
      }

      @Override
      protected void describeMismatchSafely(Student item, Description mismatchDescription) {
        mismatchDescription.appendText("but was ");
        mismatchDescription.appendValue(item);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("expect to be ");
        description.appendValue(user);
      }
    };
  }
}
