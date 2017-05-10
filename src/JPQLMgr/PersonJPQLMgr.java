package JPQLMgr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Person;

/**
 * A Java Persistence Query Language Manager which would send query for
 * {@link Person Person} to the connected database. There are methods to find,
 * add, and modify {@link Person Person} to/from the database.
 *
 * @author Shiyao Zhang
 */
public class PersonJPQLMgr {

    /**
     * The persistence unit used to connect database.
     */
    private final String persistence_unit;

    /**
     * Constructs a JPQL Manager connecting to database.
     *
     * @param pu The persistence unit.
     */
    public PersonJPQLMgr(String pu) {
        persistence_unit = pu;
    }

    /**
     * Find a {@link Person Person} by id.
     *
     * @param id Id of {@link Person Person} to be found.
     * @return <code>null</code> if no {@link Person Person} found.
     */
    public Person find(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Person p = em.find(Person.class, id);

        userTransaction.commit();
        em.close();
        emf.close();

        return p;
    }

    /**
     * Add a {@link Person Person} to the database.
     *
     * @param p {@link Person Person} to be added to the database.
     */
    public void add(Person p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        em.persist(p);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    /**
     * Modify information of a {@link Person Person} inside the database.
     *
     * @param p The modified {@link Person Person}.
     * @throws IllegalArgumentException if entity does not exist in the
     * database.
     */
    public void modify(Person p) throws IllegalArgumentException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        em.merge(p);

        userTransaction.commit();
        em.close();
        emf.close();
    }
}
