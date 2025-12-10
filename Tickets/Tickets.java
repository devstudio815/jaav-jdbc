package Tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tickets {

    public void Delete(Connection conn, int id) {
        try {
            if (conn != null) {
                String query = """
                        delete from tickets where id = ?
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                System.out.print("delete animals home successfully");
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("Exception is " + e.getMessage());

        }
    }

    public void Create(Connection conn, String name, int price, int zoo_id) {
        try {
            if (conn != null) {

                String query = """
                        insert into tickets (name,price,zoo_id)
                        values (?,?,?)
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, zoo_id);
                preparedStatement.executeUpdate();

                System.out.print("create tickets successfully");
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

    public void FindAll(Connection conn) {
        try {
            if (conn != null) {

                Statement statement = conn.createStatement();
                String query = """
                    select 
                        t.id,
                        t.name,
                        t.price,
                        z.name as zoo_name
                    from tickets t 
                    left join zoo z 
                    on z.id = t.zoo_id
                """;
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");
                    String zoo = resultSet.getString("zoo_name");
                    System.out.println("ID: " + id + ", name : " + name + ", harga : " + price + ", Kebun Binantang : " + zoo);
                }
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

   public void FindByZooid(Connection conn, int zooid) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        if (conn != null) {
            String query = """
                SELECT 
                    t.id,
                    t.name,
                    t.price,
                    z.name AS zoo_name
                FROM tickets t 
                LEFT JOIN zoo z ON z.id = t.zoo_id
                WHERE t.zoo_id = ?
            """;
            
            statement = conn.prepareStatement(query);
            statement.setInt(1, zooid);
            
            resultSet = statement.executeQuery();
            
            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String zoo = resultSet.getString("zoo_name");
                System.out.println("ID: " + id + ", name: " + name + ", harga: " + price + ", Kebun Binatang: " + zoo);
            }
            
            if (!hasResults) {
                System.out.println("Tidak ada tiket ditemukan untuk zoo_id: " + zooid);
            }
        } else {
            System.out.println("Koneksi database tidak tersedia");
        }
    } catch (SQLException e) {
        System.out.println("Error saat mengakses database: " + e.getMessage());
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        } catch (SQLException e) {
            System.out.println("Error saat menutup resources: " + e.getMessage());
        }
    }
}

    public void Update(Connection conn, String name, int id, int price, int zoo_id) {
        try {
            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE zoo SET ");
                List<Object> values = new ArrayList<>();

                if (name != null && !name.isEmpty()) {
                    query.append("name = ?, ");
                    values.add(name);
                }

                if (price != 0) {
                    query.append("price = ?, ");
                    values.add(price);
                }

                if (zoo_id != 0) {
                    query.append("zoo_id = ? ");
                    values.add(zoo_id);
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
}
