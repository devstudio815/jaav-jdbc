import Animals.Animals;
import Animals.Models;

class Main {
    public static void main(String[] args) {
        Config config = new Config();
        Animals animals = new Animals();
        Models models = new Models();
        models.SetName("Sapi");
        models.SetAnimal_home_id(2);

        animals.Update(config.connect(), models.GetName(), 4, 1);
        // animals.Update(config.connect(), models.GetName(), 1, 1);
        // models.GetAnimal_home_id()
        animals.FindAll(config.connect());
    }

}