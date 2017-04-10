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

}
