package Animals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Animals {

    public void Create(Connection conn, String name, int animal_ticket_id) {
        try {
            if (conn != null) {
                String query = """
                        insert into animals (name,animal_ticket_id)
                        values (?,?)
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, animal_ticket_id);
                preparedStatement.executeUpdate();

                System.out.print("create animals successfully");
            } else {
                System.out.print("ERROR");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

    public void FindAll(Connection conn) {
        try {
            if (conn != null) {

                Statement statement = conn.createStatement();
                String query = "select * from animals";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("ID: " + id + ", name : " + name);
                }
            } else {
                System.out.print("ERROR");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

    public void FindAnimalByTicketId(Connection conn, int ticketId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            if (conn != null) {
                String query = """
                SELECT 
                    a.name as animal_name,
                    t.name as ticket_name,
                    t.price as ticket_price
                FROM animal_ticket at
                LEFT JOIN tickets t ON t.id = at.ticket_id
                left join animals a on a.id = at.animal_id
                WHERE at.ticket_id = ?
            """;

                statement = conn.prepareStatement(query);
                statement.setInt(1, ticketId);

                resultSet = statement.executeQuery();

                boolean hasResults = false;
                while (resultSet.next()) {
                    hasResults = true;
                    String name = resultSet.getString("animal_name");
                    String ticketName = resultSet.getString("ticket_name");
                    int price = resultSet.getInt("ticket_price");
                    System.out.println("Nama Hewan: " + name + ", Nama Ticket: " + ticketName + ", Harga : " + price);
                }

                if (!hasResults) {
                    System.out.println("Tidak ada tiket ditemukan untuk zoo_id: " + ticketId);
                }
            } else {
                System.out.println("Koneksi database tidak tersedia");
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengakses database: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error saat menutup resources: " + e.getMessage());
            }
        }
    }

    public void Update(Connection conn, String name, int id, int animalHomeId) {
        try {
            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE animals SET ");
                List<Object> values = new ArrayList<>();

                if (name != null && !name.isEmpty()) {
                    query.append("name = ?, ");
                    values.add(name);
                }

                if (animalHomeId > 0) {
                    query.append("animal_ticket_id = ?, ");
                    values.add(animalHomeId);
                }

                // Hapus koma terakhir
                if (values.isEmpty()) {
                    System.out.println("No fields to update");
                    return;
                }

                query.setLength(query.length() - 2);
                query.append(" WHERE id = ?");
                values.add(id);

                // Set parameters
                try (PreparedStatement statement = conn.prepareStatement(query.toString())) {
                    // Set parameters
                    for (int i = 0; i < values.size(); i++) {
                        statement.setObject(i + 1, values.get(i));
                    }

                    int rowsAffected = statement.executeUpdate();
                    System.out.println("Updated " + rowsAffected + " row(s)");
                }

            } else {
                System.out.println("Not connected!");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void Delete(Connection conn, int id) {
        try {
            if (conn != null) {
                String query = """
                        delete from animals where id = ?
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                System.out.print("delete animals successfully");
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

}
