import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Sighting extends Animal implements DatabaseReqInterface {
  private int sighting_id;
  private int animal_id;
  private int ranger_id;
  private String location;
  private String sighting_date;
  private String is_endangered;
  private Timestamp timestamp_sighting_date;
  private String formatted;
  private String endangered;


  public Sighting(int animal_id, int ranger_id, String location, String sighting_date) {
    this.animal_id = animal_id;
    this.ranger_id = ranger_id;
    this.location = location;
    this.sighting_date = sighting_date;
    this.is_endangered = isEndangered(animal_id).get(0);
    this.timestamp_sighting_date = stringToTimestamp(sighting_date);
  }

  public int getSightingId() {
    return sighting_id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public int getRangerId() {
    return ranger_id;
  }

  public String getLocation() {
    return location;
  }

  public String getSightingDate() {
    return sighting_date;
  }

  public String getIsEndangered() {
    return is_endangered;
  }

  public Timestamp stringToTimestamp(String input) {
    formatted = input + ":00";
    return Timestamp.valueOf(formatted);
  }

  public static List<String> isEndangered(int animal_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT is_endangered FROM animals WHERE animal_id = :animal_id;";
      return con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .throwOnMappingFailure(false)
        .executeAndFetch(String.class);
    }
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == newSighting.getAnimalId() &&
             this.getLocation().equals(newSighting.getLocation()) &&
             this.getSightingDate().equals(newSighting.getSightingDate()) &&
             this.getRangerId() == newSighting.getRangerId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, ranger_id, location, sighting_date, is_endangered, timestamp_sighting_date) VALUES (:animal_id, :ranger_id, :location, :sighting_date, :is_endangered, :timestamp_sighting_date);";
      this.sighting_id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("ranger_id", this.ranger_id)
        .addParameter("location", this.location)
        .addParameter("sighting_date", this.sighting_date)
        .addParameter("is_endangered", this.is_endangered)
        .addParameter("timestamp_sighting_date", this.timestamp_sighting_date)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int sighting_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE sighting_id = :sighting_id;";
      return con.createQuery(sql)
        .addParameter("sighting_id", sighting_id)
        .executeAndFetchFirst(Sighting.class);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM sightings WHERE sighting_id=:sighting_id;";
      con.createQuery(sql)
      .addParameter("sighting_id", sighting_id)
      .executeUpdate();
    }
  }

  public static List<Sighting> getTopSightings() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings ORDER BY timestamp_sighting_date desc;";
      return con.createQuery(sql)
        .executeAndFetch(Sighting.class);
    }
  }



}
