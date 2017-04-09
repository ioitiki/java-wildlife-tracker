import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
import java.time.*;
import java.text.*;

public abstract class Animal {
  public int animal_id;
  public String name;
  public String location;
  public Timestamp last_sighting;
  public String description;
  public String is_endangered;

  public static final String LOCATION_ZONE_1 = "NE Quadrant";
  public static final String LOCATION_ZONE_2 = "SE Quadrant";
  public static final String LOCATION_ZONE_3 = "NW Quadrant";
  public static final String LOCATION_ZONE_4 = "SW Quadrant";

  public int getAnimalId() {
    return animal_id;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    if(this.location.equals(location_zone_1)) {
      return location_zone_1;
    } elseif(this.location.equals(location_zone_1)) {
      return location_zone_1;
    } elseif(this.location.equals(location_zone_1)) {
      return location_zone_1;
    } elsef(this.location.equals(location_zone_1)) {
      return location_zone_1;
    } else {
      throw new IllegalArgumentException("The location you've selected is not known area.");
      return "INPUT_ERROR";
    }
  }

  public String getLocation() {
    if(location_zone_1){
      return endangered;
    } else {
      return not_endangered;
    }
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals;";
      return con.createQuery(sql)
        .executeAndFetch(Animal.class);
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }

}
