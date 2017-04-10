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

public class AnimalSightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animalSighting_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("Rick", "test@email.com", "386-233-5467", 5248);
    testRanger.save();
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal.save();
    Timestamp testTimestamp = new Timestamp(1, 2, 3, 4, 5, 6, 7);
    AnimalSighting testSighting = new AnimalSighting(testEndangeredAnimal.getAnimalId(), testRanger.getRangerId(), 2, testTimestamp);
    assertEquals(true, testSighting instanceof AnimalSighting);
  }

  @Test
  public void animalSighting_instantiatesWithCorrectIsEndangered_true() {
    Ranger testRanger = new Ranger("Rick", "test@email.com", "386-233-5467", 5248);
    testRanger.save();
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal.save();
    Timestamp testTimestamp = new Timestamp(1, 2, 3, 4, 5, 6, 7);
    AnimalSighting testSighting = new AnimalSighting(testEndangeredAnimal.getAnimalId(), testRanger.getRangerId(), 2, testTimestamp);
    assertEquals("Endangered", testSighting.getIsEndangered());
  }

  @Test
  public void formatTimestamp_returnFormattedStringGivenATimestamp_string() {
    Ranger testRanger = new Ranger("Rick", "test@email.com", "386-233-5467", 5248);
    testRanger.save();
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 2, "Brown with grey tail.");
    testEndangeredAnimal.save();
    Timestamp testTimestamp = new Timestamp(1, 2, 3, 4, 5, 6, 7);
    AnimalSighting testSighting = new AnimalSighting(testEndangeredAnimal.getAnimalId(), testRanger.getRangerId(), 2, testTimestamp);
    assertEquals("Mar 3, 1901 4:05:06 AM", AnimalSighting.formatTimestamp(testSighting.getSightingDate()));
  }

}
