import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class EndangeredAnimal extends Animal implements DatabaseReqInterface {
  private int health;
  private int age;

  private static final String ENDANGERED = "Endangered";
  public static final List<String> HEALTH_STATUSES = new ArrayList<String>("healthy", "okay", "ill");
  public static final List<String> AGE_TYPES = new ArrayList<String>("newborn", "young", "adult");

  public EndangeredAnimal(String name, String is_endangered, int health_number, int age_number, String description, String location) {
    this.name = name;
    is_endangered = ENDANGERED;
    health = health_number;
    age = age_number;
    this.description = description;
    this.location = getLocation(location);
  }

  public String getHealth() {
    return HEALTH_STATUSES.get(health);
  }

  public String getAge() {
    return AGE_TYPES.get(age);
  }



}
