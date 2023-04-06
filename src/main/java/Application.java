import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City ufa = new City(5, "Ufa");
            Employee employee1 = new Employee("Ekaterina", "Bolshakova", "female", 26, ufa);
            employeeDAO.create(employee1);

            List<Employee> employeeList = new ArrayList<>(employeeDAO.getAll());

            for (Employee employee : employeeList) {
                System.out.println(employee);
            }

            employeeDAO.deleteById(11);
            employeeDAO.updateById(12, "Valentina", "Boyarinova", "female", 21, ufa);

            for (Employee employee : employeeList) {
                System.out.println(employee);
            }


        }
    }

}
