import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);

    Employee getById(int id);

    List<Employee> getAll();

    void updateById(int id, String firstName, String lastName, String gender, int age, City cityName);

    void deleteById(int id);

}
