import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Ranger {
  private int ranger_id;
  private String name;
  private String email;
  private String phone_number;
  private int badge_number;

  public Ranger(String name, String email, String phone_number, int badge_number) {
    this.name = name;
    this.email = email;
    this.phone_number = phone_number;
    this.badge_number = badge_number;
  }

  public int getRangerId() {
    return ranger_id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phone_number;
  }

  public int getBadgeNumber() {
    return badge_number;
  }

  @Override
  public boolean equals(Object otherRanger) {
    if(!(otherRanger instanceof Ranger)) {
      return false;
    } else {
      Ranger newRanger = (Ranger) otherRanger;
      return this.getName().equals(newRanger.getName()) &&
             this.getBadgeNumber() == newRanger.getBadgeNumber();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO rangers (name, email, phone_number, badge_number) VALUES (:name, :email, :phone_number, :badge_number);";
      this.ranger_id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("email", this.email)
        .addParameter("phone_number", this.phone_number)
        .addParameter("badge_number", this.badge_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Ranger> all() {
    String sql = "SELECT * FROM rangers where is_endangered='Endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Ranger.class);
    }
  }

  public static Ranger find(int ranger_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers where ranger_id=:ranger_id";
      return con.createQuery(sql)
        .addParameter("ranger_id", ranger_id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Ranger.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM rangers WHERE ranger_id=:ranger_id;";
      con.createQuery(sql)
      .addParameter("ranger_id", ranger_id)
      .executeUpdate();
    }
  }
}
