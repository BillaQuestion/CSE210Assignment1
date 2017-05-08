/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Bill
 */
public class FriendsJPQLMgr {

    private final String persistence_unit;

    public FriendsJPQLMgr(String pu) {
        persistence_unit = pu;
    }

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

    public Set<Friends> find(String myid) {
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

    public void tryIsFriend(String myId, String friendId) throws NoResultException {
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
//            userTransaction.commit();
            em.close();
            emf.close();
        }

    }
}
