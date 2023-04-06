import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);

    Employee getById(int id);

    List<Employee> getAll();

    void updateById(int id, String first_name, String last_name, String gender, int age, City city_name);

    void deleteById(int id);

}
