package Tickets;

public class Models {
    int ID;
    String name;
    int price;
    int zooId;

    public void SetName(String req){
        name = req;
    }

    public  void SetPrice(int Price){
        price = Price;
    }

    public void SetZooID(int zooid){
        zooId = zooid;
    }

    public String GetName(){
        return this.name;
    }

    public int GetPrice(){
        return this.price;
    }
    public int GetZooId(){
        return this.zooId;
    }

    public int GetId(){
        return this.ID;
    }
}
