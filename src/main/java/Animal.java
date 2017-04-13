import org.sql2o.*;
import java.util.List;
import java.util.Arrays;

public abstract class Animal implements DatabaseReqInterface {
  public int animal_id;
  public String name;
  public String description;
  public String is_endangered;
  public String health;
  public String age;

  public int getAnimalId() {
    return animal_id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  public String getIsEndangered() {
    return is_endangered;
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
             this.getDescription().equals(newAnimal.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, description, is_endangered, health, age) VALUES (:name, :description, :is_endangered, :health, :age);";
      this.animal_id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("is_endangered", this.is_endangered)
        .addParameter("health", this.health)
        .addParameter("age", this.age)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE animal_id = :animal_id;";
      con.createQuery(sql)
        .addParameter("animal_id", animal_id)
        .executeUpdate();
    }
  }

  public void update(String name, String description, String health, String age) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET (name, description, health, age) = (:name, :description, :is_endangered, :health, :age) WHERE animal_id = :animal_id;";
      con.createQuery(sql)
        .addParameter("animal_id", this.animal_id)
        .addParameter("name", name)
        .addParameter("description", description)
        .addParameter("health", health)
        .addParameter("age", age)
        .executeUpdate();
    }
  }


}
