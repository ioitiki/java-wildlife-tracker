import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class EndangeredAnimal extends Animal {
  private int health;
  private int age;

  private static final String ENDANGERED = "Endangered";
  private static final List<String> HEALTH_STATUSES = Arrays.asList("healthy", "okay", "ill");
  private static final List<String> AGE_TYPES = Arrays.asList("newborn", "young", "adult");


  public EndangeredAnimal(String name, int health_number, int age_number, String description) {
    this.name = name;
    is_endangered = ENDANGERED;
    this.health = health_number;
    this.age = age_number;
    this.description = description;
  }

  public String getHealth() {
    return HEALTH_STATUSES.get(health);
  }

  public String getAge() {
    return AGE_TYPES.get(age);
  }


}
