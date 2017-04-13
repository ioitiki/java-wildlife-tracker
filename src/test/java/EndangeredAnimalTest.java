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
  private EndangeredAnimal testEndangeredAnimal1;
  private EndangeredAnimal testEndangeredAnimal2;
  private EndangeredAnimal testEndangeredAnimal3;

  @Before
  public void setUp() {
    testEndangeredAnimal1 = new EndangeredAnimal("Bear", "grey and brown fur.", "healthy", "young");
    testEndangeredAnimal1.save();
    testEndangeredAnimal2 = new EndangeredAnimal("Fox", "red with brown fur.", "ill", "young");
    testEndangeredAnimal2.save();
    testEndangeredAnimal3 = new EndangeredAnimal("Bear", "grey and brown fur.", "healthy", "young");
    testEndangeredAnimal3.save();
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void EndangeredAnimal_instantiatesCorrectly_true() {
    assertEquals(true, testEndangeredAnimal1 instanceof EndangeredAnimal);
  }

  @Test
  public void allGetters_returnsDesiredPropertyOfEndangeredAnimal_true() {
    assertEquals("Bear", testEndangeredAnimal1.getName());
    assertEquals("grey and brown fur.", testEndangeredAnimal1.getDescription());
    assertEquals("healthy", testEndangeredAnimal1.getHealth());
    assertEquals("young", testEndangeredAnimal1.getAge());
  }

  @Test
  public void equals_returnsTrueIfNameAndDescriptionAreSame_true() {
    assertTrue(testEndangeredAnimal1.equals(testEndangeredAnimal3));
  }

  @Test
  public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
    assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal1));
  }

  @Test
  public void save_assignsAnimalIdToEndangeredAnimal() {
    EndangeredAnimal savedEndangeredAnimal1 = EndangeredAnimal.all().get(0);
    EndangeredAnimal savedEndangeredAnimal2 = EndangeredAnimal.all().get(1);
    EndangeredAnimal savedEndangeredAnimal3 = EndangeredAnimal.all().get(2);
    assertEquals(savedEndangeredAnimal1.getAnimalId(), testEndangeredAnimal1.getAnimalId());
    assertEquals(savedEndangeredAnimal2.getAnimalId(), testEndangeredAnimal2.getAnimalId());
    assertEquals(savedEndangeredAnimal3.getAnimalId(), testEndangeredAnimal3.getAnimalId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    assertEquals(true, EndangeredAnimal.all().get(0).equals(testEndangeredAnimal1));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(testEndangeredAnimal2));
    assertEquals(true, EndangeredAnimal.all().get(2).equals(testEndangeredAnimal3));
  }

  @Test
  public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
    assertEquals(EndangeredAnimal.find(testEndangeredAnimal2.getAnimalId()), testEndangeredAnimal2);
  }

  @Test
  public void delete_deletesEndangeredAnimalFromDatabase_0() {
    testEndangeredAnimal1.delete();
    testEndangeredAnimal2.delete();
    testEndangeredAnimal3.delete();
    assertEquals(0, EndangeredAnimal.all().size());
  }

}
