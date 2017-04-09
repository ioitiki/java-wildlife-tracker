import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteDatabaseReqInterfaceQuery = "DELETE FROM database_req_interfaces *;";
      con.createQuery(deleteDatabaseReqInterfaceQuery).executeUpdate();
      String deleteRangerQuery = "DELETE FROM rangers *;";
      con.createQuery(deleteRangerQuery).executeUpdate();
      String deleteNotEndangeredAnimalQuery = "DELETE FROM not_endangered_animals *;";
      con.createQuery(deleteNotEndangeredAnimalQuery).executeUpdate();
      String deleteEndangeredAnimalQuery = "DELETE FROM endangered_animals *;";
      con.createQuery(deleteEndangeredAnimalQuery).executeUpdate();
      String deleteAnimalSightingQuery = "DELETE FROM animal_sightings *;";
      con.createQuery(deleteAnimalSightingQuery).executeUpdate();
      String deleteAnimalQuery = "DELETE FROM animals *;";
      con.createQuery(deleteAnimalQuery).executeUpdate();
    }
  }
}
