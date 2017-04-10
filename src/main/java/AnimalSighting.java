import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class AnimalSighting extends Animal {
  private int sighting_id;
  private int ranger_id;
  private int location;
  private Timestamp sighting_date;

  private static final List<String> LOCATION_ZONES = Arrays.asList("NE Quadrant", "SE Quadrant", "NW Quadrant", "SW Quadrant");


  public AnimalSighting(int ranger_id, int location, Timestamp sighting_date, String description) {
    this.ranger_id = ranger_id;
    this.location = location;
    this.sighting_date = sighting_date;
    this.description = description;
  }

  public int getSightingId() {
    return sighting_id;
  }

  public int getRangerId() {
    return ranger_id;
  }

  // public String getRangerName() {
  //   return Ranger.find(this.ranger_id).getTitle();
  // }

  public String getLocation() {
    return LOCATION_ZONES.get(location);
  }

  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO sightings (animal_id, ranger_name, location, is_endangered) VALUES (:animal_id, :ranger_name, :location, :is_endangered);";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("animal_id", this.animal_id)
  //       .addParameter("ranger_name", this.ranger_name)
  //       .addParameter("location", this.location)
  //       .addParameter("is_endangered", this.is_endangered)
  //       .addParameter("animal_id", this.animal_id)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }

}
