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
  private NotEndangeredAnimal testNotEndangeredAnimal1;
  private NotEndangeredAnimal testNotEndangeredAnimal2;
  private NotEndangeredAnimal testNotEndangeredAnimal3;

  @Before
  public void setUp() {
    testNotEndangeredAnimal1 = new NotEndangeredAnimal("Bear", "grey and brown fur.", "healthy", "young");
    testNotEndangeredAnimal1.save();
    testNotEndangeredAnimal2 = new NotEndangeredAnimal("Fox", "red with brown fur.", "ill", "young");
    testNotEndangeredAnimal2.save();
    testNotEndangeredAnimal3 = new NotEndangeredAnimal("Bear", "grey and brown fur.", "healthy", "young");
    testNotEndangeredAnimal3.save();
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void NotEndangeredAnimal_instantiatesCorrectly_true() {
    assertEquals(true, testNotEndangeredAnimal1 instanceof NotEndangeredAnimal);
  }

  @Test
  public void allGetters_returnsDesiredPropertyOfNotEndangeredAnimal_true() {
    assertEquals("Bear", testNotEndangeredAnimal1.getName());
    assertEquals("grey and brown fur.", testNotEndangeredAnimal1.getDescription());
    assertEquals("healthy", testNotEndangeredAnimal1.getHealth());
    assertEquals("young", testNotEndangeredAnimal1.getAge());
  }

  @Test
  public void equals_returnsTrueIfNameAndDescriptionAreSame_true() {
    assertTrue(testNotEndangeredAnimal1.equals(testNotEndangeredAnimal3));
  }

  @Test
  public void save_successfullyAddsNotEndangeredAnimalToDatabase_List() {
    assertTrue(NotEndangeredAnimal.all().get(0).equals(testNotEndangeredAnimal1));
  }

  @Test
  public void save_assignsAnimalIdToNotEndangeredAnimal() {
    NotEndangeredAnimal savedNotEndangeredAnimal1 = NotEndangeredAnimal.all().get(0);
    NotEndangeredAnimal savedNotEndangeredAnimal2 = NotEndangeredAnimal.all().get(1);
    NotEndangeredAnimal savedNotEndangeredAnimal3 = NotEndangeredAnimal.all().get(2);
    assertEquals(savedNotEndangeredAnimal1.getAnimalId(), testNotEndangeredAnimal1.getAnimalId());
    assertEquals(savedNotEndangeredAnimal2.getAnimalId(), testNotEndangeredAnimal2.getAnimalId());
    assertEquals(savedNotEndangeredAnimal3.getAnimalId(), testNotEndangeredAnimal3.getAnimalId());
  }

  @Test
  public void all_returnsAllInstancesOfNotEndangeredAnimal_true() {
    assertEquals(true, NotEndangeredAnimal.all().get(0).equals(testNotEndangeredAnimal1));
    assertEquals(true, NotEndangeredAnimal.all().get(1).equals(testNotEndangeredAnimal2));
    assertEquals(true, NotEndangeredAnimal.all().get(2).equals(testNotEndangeredAnimal3));
  }

  @Test
  public void find_returnsNotEndangeredAnimalWithSameId_secondNotEndangeredAnimal() {
    assertEquals(NotEndangeredAnimal.find(testNotEndangeredAnimal2.getAnimalId()), testNotEndangeredAnimal2);
  }

  @Test
  public void delete_deletesNotEndangeredAnimalFromDatabase_0() {
    testNotEndangeredAnimal1.delete();
    testNotEndangeredAnimal2.delete();
    testNotEndangeredAnimal3.delete();
    assertEquals(0, NotEndangeredAnimal.all().size());
  }

}
