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

public class SightingTest {
  private Ranger testRanger;
  private NotEndangeredAnimal testAnimal1;
  private EndangeredAnimal testAnimal2;
  private Sighting testSighting1;
  private Sighting testSighting2;
  private Sighting testSighting3;

  @Before
  public void setUp() {
    testRanger = new Ranger("Rick", "test@email.com", "386-233-5467", 5248);
    testRanger.save();
    testAnimal1 = new NotEndangeredAnimal("Fox", "red with brown fur.", "ill", "young");
    testAnimal1.save();
    testAnimal2 = new EndangeredAnimal("Bear", "grey and brown fur.", "healthy", "young");
    testAnimal2.save();
    testSighting1 = new Sighting(testAnimal1.getAnimalId(), testRanger.getRangerId(), "NE Quadrant", "2017-04-04 22:30");
    testSighting1.save();
    testSighting2 = new Sighting(testAnimal2.getAnimalId(), testRanger.getRangerId(), "SE Quadrant", "2016-04-04 22:30");
    testSighting2.save();
    testSighting3 = new Sighting(testAnimal2.getAnimalId(), testRanger.getRangerId(), "SE Quadrant", "2016-12-04 10:30");
    testSighting3.save();
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Sighting_instantiatesCorrectly_true() {
    assertEquals(true, testSighting1 instanceof Sighting);
  }

  @Test
  public void allGetters_returnsDesiredPropertyOfSighting_true() {
    assertEquals("Endangered", testSighting2.getIsEndangered());
    assertEquals("Not Endangered", testSighting1.getIsEndangered());
    assertEquals("SE Quadrant", testSighting2.getLocation());
    assertEquals("2016-04-04 22:30", testSighting2.getSightingDate());
  }

  @Test
  public void save_successfullyAddsSightingToDatabase_true() {
    assertTrue(Sighting.all().get(0).equals(testSighting1));
    assertTrue(Sighting.all().get(1).equals(testSighting2));
    assertTrue(Sighting.all().get(2).equals(testSighting3));
  }

  @Test
  public void save_assignsAnimalIdToNotEndangeredAnimal() {
    Sighting savedSighting1 = Sighting.all().get(0);
    Sighting savedSighting2 = Sighting.all().get(1);
    Sighting savedSighting3 = Sighting.all().get(2);
    assertEquals(savedSighting1.getAnimalId(), testSighting1.getAnimalId());
    assertEquals(savedSighting2.getAnimalId(), testSighting2.getAnimalId());
    assertEquals(savedSighting3.getAnimalId(), testSighting3.getAnimalId());
  }


}
