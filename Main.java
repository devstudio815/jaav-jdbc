
import Zoos.Zoos;
import java.sql.Connection;

class Main {

    public static void main(String[] args) {
        Config config = new Config();
        Zoos zoos = new Zoos();
        Connection conn = config.connect();
        zoos.FindAll(conn);

    }

}
