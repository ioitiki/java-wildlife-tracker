import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class NotEndangeredAnimal extends Animal {

  public static final String ENDANGERED = "Not Endangered";

  public NotEndangeredAnimal(String name, String description, String health, String age) {
    is_endangered = ENDANGERED;
    this.name = name;
    this.description = description;
    this.health = health;
    this.age = age;
  }

  public static List<NotEndangeredAnimal> all() {
    String sql = "SELECT * FROM animals where is_endangered = 'Not Endangered';";
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
