package Animals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Animals {
    public void Create(Connection conn, String name, int Animal_home_id) {
        try {
            if (conn != null) {

                String query = """
                        insert into animals (name,animal_home_id)
                        values (?,?)
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, Animal_home_id);
                preparedStatement.executeUpdate();

                System.out.print("create animals successfully");
            } else {
                System.out.print("ga connnect cok");
            }
        } catch (Exception e) {
            System.out.print("asu");
            System.out.println("Exception is " + e.getMessage());

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
                System.out.print("ga connnect cok");
            }
        } catch (Exception e) {
            System.out.print("asu");
            System.out.println("Exception is " + e.getMessage());

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
                    query.append("animal_home_id = ?, ");
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

                PreparedStatement statement = conn.prepareStatement(query.toString());

                // Set parameters
                for (int i = 0; i < values.size(); i++) {
                    statement.setObject(i + 1, values.get(i));
                }

                int rowsAffected = statement.executeUpdate();
                System.out.println("Updated " + rowsAffected + " row(s)");

                statement.close();

            } else {
                System.out.println("Not connected!");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
