package JPQLMgr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import model.Friends;

/**
 * A Java Persistence Query Language Manager which would send query for
 * {@link Friends Friends} to the connected database. There are methods to find,
 * add, and check if two <code>Person</code> are friends.
 *
 * @author Shiyao Zhang
 */
public class FriendsJPQLMgr {

    /**
     * The persistence unit used to connect database.
     */
    private final String persistence_unit;

    /**
     * Constructs a JPQL Manager connecting to database.
     *
     * @param pu The persistence unit.
     */
    public FriendsJPQLMgr(String pu) {
        persistence_unit = pu;
    }

    /**
     * Add a {@link Friends Friends} relation of two <code>Person</code>s to the
     * database.
     *
     * @param mid The id of the first Person in friendship.
     * @param fid The id of the second Person in friendship.
     */
    public void add(String mid, String fid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        em.persist(new Friends(mid, fid));

        userTransaction.commit();
        em.close();
        emf.close();
    }

    /**
     * Find all Friends of a specific <code>Person</code>. This method would
     * only search one direction friendship which is "my id - other id" pair.
     *
     * @param myid Id of the <code>Person</code> you want to search.
     * @return A set of {@link Friends Friends} represents all "my id - other
     * id" pair. Result would not contain any "other id - my id" pair.
     */
    public Set<Friends> findFriends(String myid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Friends> lf1 = em.createQuery("SELECT c FROM model.Friends c WHERE c.myId LIKE :myId")
                .setParameter("myId", myid)
                .getResultList();

        userTransaction.commit();
        em.close();
        emf.close();

        Set<Friends> sf1 = new HashSet(lf1);
        return sf1;
    }

    /**
     * Check if two <code>Person</code>s are friends. This method would check
     * double direction of friendship e.g. both "my id - other id" pair and
     * "other id - my id" pair.
     *
     * @param myId Id of the first <code>Person</code>.
     * @param friendId Id of the second <code>Person</code>.
     * @throws NoResultException if these two <code>Person</code>s are not
     * friends.
     */
    public void tryFriend(String myId, String friendId) throws NoResultException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f;
        try {
            f = (Friends) em.createQuery("SELECT c FROM model.Friends c WHERE c.myId LIKE :myId AND c.friendId LIKE :friendId")
                    .setParameter("myId", myId)
                    .setParameter("friendId", friendId)
                    .getSingleResult();
            userTransaction.commit();

        } catch (NoResultException nre) {
            f = (Friends) em.createQuery("SELECT c FROM model.Friends c WHERE c.myId LIKE :myId AND c.friendId LIKE :friendId")
                    .setParameter("myId", friendId)
                    .setParameter("friendId", myId)
                    .getSingleResult();
            userTransaction.commit();

        } finally {
            em.close();
            emf.close();
        }

    }
}
