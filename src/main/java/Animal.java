import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
import java.time.*;
import java.text.*;

public abstract class Animal implements DatabaseReqInterface {
  public int animal_id;
  public String name;
  // public Timestamp last_sighting_timestamp;
  // public String last_sighting;
  public String description;
  public String is_endangered;
  public String health;
  public String age;

  // DateFormat.getDateTimeInstance().format(testTimestamp);


  public static final List<String> HEALTH_STATUSES = Arrays.asList("healthy", "okay", "ill");
  public static final List<String> AGE_TYPES = Arrays.asList("newborn", "young", "adult");


  public int getAnimalId() {
    return animal_id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
             this.getDescription().equals(newAnimal.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, description, is_endangered, health, age) VALUES (:name, :description, :is_endangered, :health, :age);";
      this.animal_id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        // .addParameter("last_sighting", null)
        .addParameter("description", this.description)
        .addParameter("is_endangered", this.is_endangered)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE animal_id=:animal_id;";
      con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .executeUpdate();
    }
  }

  public List<AnimalSighting> getAnimalSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:animal_id;";
        return con.createQuery(sql)
          .addParameter("animal_id", animal_id)
          .executeAndFetch(AnimalSighting.class);
    }
  }

}
