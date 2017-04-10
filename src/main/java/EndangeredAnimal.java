import org.sql2o.*;
import java.util.List;


public class EndangeredAnimal extends Animal {

  public static final String ENDANGERED = "Endangered";

  public EndangeredAnimal(String name, int health_number, int age_number, String description) {
    this.name = name;
    is_endangered = ENDANGERED;
    this.health = HEALTH_STATUSES.get(health_number - 1);
    this.age = AGE_TYPES.get(age_number - 1);
    this.description = description;
  }

  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals where is_endangered='Endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int animal_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where animal_id=:animal_id";
      return con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }


}
