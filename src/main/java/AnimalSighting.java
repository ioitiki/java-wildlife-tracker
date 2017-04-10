import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class AnimalSighting extends Animal implements DatabaseReqInterface {
  private int sighting_id;
  private int animal_id;
  private int ranger_id;
  private String location;
  private Timestamp sighting_date;
  private String is_endangered;

  private static final List<String> LOCATION_ZONES = Arrays.asList("NE Quadrant", "SE Quadrant", "NW Quadrant", "SW Quadrant");


  public AnimalSighting(int animal_id, int ranger_id, int location_number, Timestamp sighting_date) {
    this.animal_id = animal_id;
    this.ranger_id = ranger_id;
    this.location = LOCATION_ZONES.get(location_number - 1);
    this.sighting_date = sighting_date;
    this.is_endangered = getIsEndangeredList(animal_id).get(0);
  }

  public int getSightingId() {
    return sighting_id;
  }

  public int getRangerId() {
    return ranger_id;
  }

  public String getIsEndangered() {
    return is_endangered;
  }

  public String getRangerName() {
    return Ranger.find(this.ranger_id).getName();
  }

  public String getLocation() {
    return location;
  }

  public static List<String> getIsEndangeredList(int animal_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT is_endangered FROM animals WHERE animal_id=:animal_id;";
        return con.createQuery(sql)
          .addParameter("animal_id", animal_id)
          .executeAndFetch(String.class);
    }
  }

  @Override
  public boolean equals(Object otherAnimalSighting) {
    if(!(otherAnimalSighting instanceof AnimalSighting)) {
      return false;
    } else {
      AnimalSighting newAnimalSighting = (AnimalSighting) otherAnimalSighting;
      return this.getAnimalId() == newAnimalSighting.getAnimalId() &&
             this.getLocation().equals(newAnimalSighting.getLocation()) &&
             this.getRangerId() == newAnimalSighting.getRangerId();
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, ranger_id, location, sighting_date, is_endangered) VALUES (:animal_id, :ranger_id, :location, :sighting_date, :is_endangered);";
      this.sighting_id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("ranger_id", this.ranger_id)
        .addParameter("location", this.location)
        .addParameter("sighting_date", this.sighting_date)
        .addParameter("is_endangered", this.is_endangered)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<AnimalSighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(AnimalSighting.class);
    }
  }

  public static AnimalSighting find(int sighting_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE sighting_id=:sighting_id;";
      return con.createQuery(sql)
        .addParameter("sighting_id", sighting_id)
        .executeAndFetchFirst(AnimalSighting.class);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

}
