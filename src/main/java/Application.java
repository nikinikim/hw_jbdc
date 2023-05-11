import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class Application {
    public static void main(String[] args) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            CityDAO cityDAO = new CityDAOImpl(entityManager);

            City ufa = new City("Ufa");
            City kazan = new City("Kazan");

            Employee employee1 = new Employee("Ekaterina", "Bolshakova", "female", 26, ufa);
            Employee employee2 = new Employee("Ivan", "Ivanov", "male", 30, kazan);


            cityDAO.create(ufa);
            cityDAO.create(kazan);
            employeeDAO.create(employee1);
            employeeDAO.create(employee2);



            Employee retrievedEmployee = employeeDAO.getById(employee1.getId());
            System.out.println("Retrieved Employee: " + retrievedEmployee);

            retrievedEmployee.setAge(27);
            employeeDAO.update(retrievedEmployee);

            List<City> cities = cityDAO.getAll();
            List<Employee> allEmployees = employeeDAO.getAll();

            System.out.println("All Employees:");
            for (Employee employee : allEmployees) {
                System.out.println(employee);
            }

            System.out.println("Cities: ");
            for (City city : cities) {
                    System.out.println(city);
            }

            employeeDAO.delete(retrievedEmployee);

            entityManager.close();
            entityManagerFactory.close();
    }
}





