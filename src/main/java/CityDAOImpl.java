import javax.persistence.EntityManager;
import java.util.List;

public class CityDAOImpl implements CityDAO{
    private final EntityManager entityManager;

    public CityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void create(City city) {
        entityManager.persist(city);

    }

    @Override
    public City getById(int id) {
        return entityManager.find(City.class, id);
    }

    @Override
    public List<City> getAll() {
        return entityManager.createQuery("SELECT c FROM City c", City.class).getResultList();
    }

    @Override
    public void update(City city) {
        entityManager.merge(city);
    }

    @Override
    public void delete(City city) {
        entityManager.remove(city);
    }
}
