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

  @Test
  public void equals_returnsTrueIfNameAndDescriptionAreSame_true() {
    EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    assertTrue(testEndangeredAnimal1.equals(testEndangeredAnimal2));
  }

  @Test
  public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
  }

  @Test
  public void save_assignsAnimalIdToEndangeredAnimal() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
    assertEquals(savedEndangeredAnimal.getAnimalId(), testEndangeredAnimal.getAnimalId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal1.save();
    EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("Bear", 2, 1, "Small with some brown hair.");
    testEndangeredAnimal2.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(testEndangeredAnimal1));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(testEndangeredAnimal2));
  }

  @Test
  public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
    EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal1.save();
    EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("Bear", 2, 1, "Small with some brown hair.");
    testEndangeredAnimal2.save();
    assertEquals(EndangeredAnimal.find(testEndangeredAnimal2.getAnimalId()), testEndangeredAnimal2);
  }

  @Test
  public void delete_deletesEndangeredAnimalFromDatabase_0() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal.save();
    testEndangeredAnimal.delete();
    assertEquals(0, EndangeredAnimal.all().size());
  }

}
