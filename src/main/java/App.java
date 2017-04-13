import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("recentSightings", Sighting.getTopSightings());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/rangers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phone_number = request.queryParams("phone-number");
      int badge_number = Integer.parseInt(request.queryParams("badge-number"));
      Ranger newRanger = new Ranger(name, email, phone_number, badge_number);
      newRanger.save();
      response.redirect("/rangers");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered-animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      EndangeredAnimal newAnimal = new EndangeredAnimal(name, description, health, age);
      newAnimal.save();
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      NotEndangeredAnimal newAnimal = new NotEndangeredAnimal(name, description, health, age);
      newAnimal.save();
      response.redirect("/animals");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/rangers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("template", "templates/rangers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<EndangeredAnimal> listOne = EndangeredAnimal.all();
      List<NotEndangeredAnimal> listTwo = NotEndangeredAnimal.all();
      List<Object> animals = new ArrayList<Object>();
      animals.addAll(listOne);
      animals.addAll(listTwo);
      model.put("animals", animals);
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int animal_id = Integer.parseInt(request.queryParams("animal"));
      int ranger_id = Integer.parseInt(request.queryParams("ranger"));
      String location = request.queryParams("location");
      String sighting_date = request.queryParams("sighting-date");
      System.out.println(sighting_date);
      Sighting newSighting = new Sighting(animal_id, ranger_id, location, sighting_date);
      newSighting.save();
      response.redirect("/sightings");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<EndangeredAnimal> listOne = EndangeredAnimal.all();
      List<NotEndangeredAnimal> listTwo = NotEndangeredAnimal.all();
      List<Object> animals = new ArrayList<Object>();
      animals.addAll(listOne);
      animals.addAll(listTwo);
      model.put("animals", animals);
      model.put("rangers", Ranger.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/sightings", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   String rangerName = request.queryParams("rangerName");
    //   int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
    //   String latLong = request.queryParams("latLong");
    //   Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
    //   sighting.save();
    //   model.put("sighting", sighting);
    //   model.put("animals", Animal.all());
    //   String animal = Animal.find(animalIdSelected).getName();
    //   model.put("animal", animal);
    //   model.put("template", "templates/animal-sighting-form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


  }
}
