import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class Application {
    public static void main(String[] args) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();

            Employee employee1 = new Employee("Ekaterina", "Bolshakova", "female", 26, 2);
            employeeDAO.create(employee1);
            Employee retrievedEmployee = employeeDAO.getById(employee1.getId());
            System.out.println("Retrieved Employee: " + retrievedEmployee);

            retrievedEmployee.setAge(27);
            employeeDAO.update(retrievedEmployee);


            List<Employee> allEmployees = employeeDAO.getAll();
            System.out.println("All Employees:");
            for (Employee employee : allEmployees) {
                System.out.println(employee);
            }

            employeeDAO.delete(retrievedEmployee);

            entityManager.close();
            entityManagerFactory.close();
    }
}





