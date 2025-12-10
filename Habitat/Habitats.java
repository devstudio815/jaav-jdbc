package Habitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Habitats {
    public void Delete(Connection conn, int id) {
        try {
            if (conn != null) {
                String query = """
                        delete from animals_home where id = ?
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

    public void Create(Connection conn, String name) {
        try {
            if (conn != null) {

                String query = """
                        insert into animals_home (name)
                        values (?)
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();

                System.out.print("create animals home successfully");
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
                String query = "select * from animals_home";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("ID: " + id + ", name : " + name);
                }
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

    public void Update(Connection conn, String name, int id) {
        try {
            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE animals_home SET ");
                List<Object> values = new ArrayList<>();

                if (name != null && !name.isEmpty()) {
                    query.append("name = ?, ");
                    values.add(name);
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
