import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class NotEndangeredAnimal extends Animal {

  public static final String ENDANGERED = "Not Endangered";

  public NotEndangeredAnimal(String name, String description) {
    is_endangered = ENDANGERED;
    this.name = name;
    this.description = description;
  }

  public static List<NotEndangeredAnimal> all() {
    String sql = "SELECT * FROM animals where is_endangered='Not Endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(NotEndangeredAnimal.class);
    }
  }

  public static NotEndangeredAnimal find(int animal_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where animal_id=:animal_id";
      return con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(NotEndangeredAnimal.class);
    }
  }

}
