import org.sql2o.*;
import java.util.List;


public class EndangeredAnimal extends Animal {

  public static final String ENDANGERED = "Endangered";

  public EndangeredAnimal(String name, String description, String health, String age) {
    is_endangered = ENDANGERED;
    this.name = name;
    this.description = description;
    this.health = health;
    this.age = age;
  }
  

  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals where is_endangered = 'Endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int animal_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where animal_id = :animal_id";
      return con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }


}
