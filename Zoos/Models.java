package Zoos;

public class Models {
    int Id;
    String Name;
    String Location;

    public void SetName(String req){
        Name = req;
    }

    public void SetLocation(String req){
        Location = req;
    }

    public String GetName(){
        return Name;
    }

    public String GetLocation(){
        return Location;
    }

    public int  GetID(){
        return Id;
    }

}
