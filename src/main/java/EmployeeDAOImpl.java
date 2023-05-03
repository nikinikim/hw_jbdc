import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (firstName, lastName, gender, age, cityId) VALUES (?, ?, ?, ?, ?)")){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityName().getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getById(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND id=(?)")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCityName(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }



    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id")){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("id"));
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String gender = resultSet.getString("gender");
                    int age = Integer.parseInt(resultSet.getString("age"));
                    City city = new City(resultSet.getInt("city_id"), resultSet.getString("city_name"));
                    employees.add(new Employee(id, firstName, lastName, gender, age, city));

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void updateById(int id, String firstName, String lastName, String gender, int age, City cityName) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET first_name=?, last_name=?, gender=?, age=?, city_id=? WHERE id=?"
        )){
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3,gender);
            statement.setInt(4, age);
            statement.setInt(5, cityName.getCityId());
            statement.setInt(6, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
