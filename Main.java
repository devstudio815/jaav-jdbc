import Animals.Animals;
import Tickets.Tickets;
import Zoos.Zoos;
import java.sql.Connection;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Config config = new Config();
        Scanner input = new Scanner(System.in);
        Zoos zoos = new Zoos();
        Tickets tickets = new Tickets();
        Animals animals = new Animals();
        Connection conn = config.connect();
        System.out.println("=======================");
        zoos.FindAll(conn);
        System.out.println("=======================");
        System.out.print("Masukkan Id Kebun Binatang : ");
        int inputzoo = input.nextInt();
        tickets.FindByZooid(conn, inputzoo);
        System.out.println("=======================");
        System.out.print("Pilih Id Ticket Diatas : ");
        int inputTicket = input.nextInt();
        animals.FindAnimalByTicketId(conn, inputTicket);
        System.out.println("=======================");

    }

}
