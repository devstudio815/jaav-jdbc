package Zoos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Zoos {
    public void Delete(Connection conn, int id) {
        try {
            if (conn != null) {
                String query = """
                        delete from zoo where id = ?
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                System.out.print("delete zoo successfully");
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("Exception is " + e.getMessage());

        }
    }

    public void Create(Connection conn, String name,String location) {
        try {
            if (conn != null) {

                String query = """
                        insert into zoo (name,location)
                        values (?,?)
                        """;
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, location);
                preparedStatement.executeUpdate();

                System.out.print("create zoo successfully");
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
                String query = "select * from zoo";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String location = resultSet.getString("location");
                    System.out.println("ID: " + id + ", name : " + name + ", location : " + location);
                }
            } else {
                System.out.print("error bossque");
            }
        } catch (SQLException e) {
            System.out.println("SQLException is " + e.getMessage());

        }
    }

    public void Update(Connection conn, String name,String location, int id) {
        try {
            if (conn != null) {
                StringBuilder query = new StringBuilder("UPDATE zoo SET ");
                List<Object> values = new ArrayList<>();

                if (name != null && !name.isEmpty()) {
                    query.append("name = ?, ");
                    values.add(name);
                }

                if (location != null && !location.isEmpty()) {
                    query.append("location = ?, ");
                    values.add(location);
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
