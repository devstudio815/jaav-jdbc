package Animals;

interface AnimalModel {
    void UpsertAnimals();
}

public class Models {
    int ID;
    String Name;
    int Animal_home_id;
    String Habitat;

    public void SetName(String req) {
        Name = req;
    }

    public String GetName() {
        return Name;
    }

    public void SetAnimal_home_id(int req) {
        Animal_home_id = req;
    }

    public int GetAnimal_home_id() {
        return Animal_home_id;
    }

}
