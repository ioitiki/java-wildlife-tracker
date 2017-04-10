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
  public Timestamp last_sighting;
  public String description;
  public String is_endangered;


  public int getAnimalId() {
    return animal_id;
  }

  public String getName() {
    return name;
  }

  public String getIsEndangered() {
    return is_endangered;
  }

  public String getDescription() {
    return description;
  }

  public Timestamp getLastSighting() {
    return last_sighting;
  }


//   @Override
//   public boolean equals(Object otherAnimal) {
//     if(!(otherAnimal instanceof Animal)) {
//       return false;
//     } else {
//       Animal newAnimal = (Animal) otherAnimal;
//       return this.getName().equals(newAnimal.getName());
//     }
//   }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO animals (name) VALUES (:name);";
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("name", this.name)
//         .executeUpdate()
//         .getKey();
//     }
//   }
//
//   public static List<Animal> all() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM animals;";
//       return con.createQuery(sql)
//         .executeAndFetch(Animal.class);
//     }
//   }
//
// public static Animal find(int animal_id) {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "SELECT * FROM animals WHERE animal_id=:animal_id;";
//     Animal animal = con.createQuery(sql)
//       .addParameter("animal_id", animal_id)
//       .executeAndFetchFirst(Animal.class);
//     return animal;
//   }
// }
//
//   public void updateName(String name) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE animals SET name=:name WHERE id=:id;";
//       con.createQuery(sql)
//         .addParameter("id", id)
//         .addParameter("name", name)
//         .executeUpdate();
//     }
//   }
//
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "DELETE FROM animals WHERE id=:id;";
//       con.createQuery(sql)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//   }
//
//   public List<Sighting> getSightings() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
//         List<Sighting> sightings = con.createQuery(sql)
//           .addParameter("id", id)
//           .executeAndFetch(Sighting.class);
//       return sightings;
//     }
//   }

}
