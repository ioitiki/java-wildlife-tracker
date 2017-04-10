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

public class NotEndangeredAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void NotEndangeredAnimal_instantiatesCorrectly_true() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    assertEquals(true, testNotEndangeredAnimal instanceof NotEndangeredAnimal);
  }

  @Test
  public void getName_returnsNameOfNotEndangeredAnimal_Fox() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    assertEquals("Fox", testNotEndangeredAnimal.getName());
  }

  @Test
  public void getDescription_returnsDescriptionOfNotEndangeredAnimal_young() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    assertEquals("Brown with grey tail.", testNotEndangeredAnimal.getDescription());
  }

  @Test
  public void equals_returnsTrueIfNameAndDescriptionAreSame_true() {
    NotEndangeredAnimal testNotEndangeredAnimal1 = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    NotEndangeredAnimal testNotEndangeredAnimal2 = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    assertTrue(testNotEndangeredAnimal1.equals(testNotEndangeredAnimal2));
  }

  @Test
  public void save_successfullyAddsNotEndangeredAnimalToDatabase_List() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    testNotEndangeredAnimal.save();
    assertTrue(NotEndangeredAnimal.all().get(0).equals(testNotEndangeredAnimal));
  }

  @Test
  public void save_assignsAnimalIdToNotEndangeredAnimal() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    testNotEndangeredAnimal.save();
    NotEndangeredAnimal savedNotEndangeredAnimal = NotEndangeredAnimal.all().get(0);
    assertEquals(savedNotEndangeredAnimal.getAnimalId(), testNotEndangeredAnimal.getAnimalId());
  }

  @Test
  public void all_returnsAllInstancesOfNotEndangeredAnimal_true() {
    NotEndangeredAnimal testNotEndangeredAnimal1 = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    testNotEndangeredAnimal1.save();
    NotEndangeredAnimal testNotEndangeredAnimal2 = new NotEndangeredAnimal("Bear", "Small with some brown hair.");
    testNotEndangeredAnimal2.save();
    assertEquals(true, NotEndangeredAnimal.all().get(0).equals(testNotEndangeredAnimal1));
    assertEquals(true, NotEndangeredAnimal.all().get(1).equals(testNotEndangeredAnimal2));
  }

  @Test
  public void find_returnsNotEndangeredAnimalWithSameId_secondNotEndangeredAnimal() {
    NotEndangeredAnimal testNotEndangeredAnimal1 = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    testNotEndangeredAnimal1.save();
    NotEndangeredAnimal testNotEndangeredAnimal2 = new NotEndangeredAnimal("Bear", "Small with some brown hair.");
    testNotEndangeredAnimal2.save();
    assertEquals(NotEndangeredAnimal.find(testNotEndangeredAnimal2.getAnimalId()), testNotEndangeredAnimal2);
  }

  @Test
  public void delete_deletesNotEndangeredAnimalFromDatabase_0() {
    NotEndangeredAnimal testNotEndangeredAnimal = new NotEndangeredAnimal("Fox", "Brown with grey tail.");
    testNotEndangeredAnimal.save();
    testNotEndangeredAnimal.delete();
    assertEquals(0, NotEndangeredAnimal.all().size());
  }

}
