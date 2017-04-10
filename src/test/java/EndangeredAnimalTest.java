import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class EndangeredAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void getName_returnsNameOfEndangeredAnimal_Fox() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    assertEquals("Fox", testEndangeredAnimal.getName());
  }

  @Test
  public void getHealth_returnsHealthOfEndangeredAnimal_healthy() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    assertEquals("healthy", testEndangeredAnimal.getHealth());
  }

  @Test
  public void getAge_returnsAgeOfEndangeredAnimal_young() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    assertEquals("young", testEndangeredAnimal.getAge());
  }

  @Test
  public void getDescription_returnsDescriptionOfEndangeredAnimal_young() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    assertEquals("Brown with grey tail.", testEndangeredAnimal.getDescription());
  }



}
