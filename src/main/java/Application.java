import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("SELECT employee.first_name, employee.last_name, employee.gender, city.city_name " +
                    "FROM employee INNER JOIN city ON employee.city_id=city.city_id " +
                    "WHERE employee.id = ?;")){

            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String first_name = "First_name: " + resultSet.getString("first_name");
                String last_name = "Last_name: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                String city = "City: " + resultSet.getString("city_name");


                System.out.println(first_name + " " + last_name + " " + gender + " " + city);


            }


        }
    }

}
