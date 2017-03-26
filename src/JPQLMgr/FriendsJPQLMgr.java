/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPQLMgr;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import model.Friends;
import model.Person;
import static test.Test.PERSISTENCE_UNIT;

/**
 *
 * @author Bill
 */
public class FriendsJPQLMgr {

    public List<Friends> find(String mid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Friends> lf = em.createQuery("SELECT c FROM model.Friends c WHERE c.myID LIKE :myId")
                .setParameter("myId", mid)
                .getResultList();

        em.close();
        emf.close();

        return lf;
    }

    public void tryIsFriend(String myId, String friendId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f;
        try {
            f = (Friends) em.createQuery("SELECT c FROM model.Friends c WHERE c.myID LIKE :myId AND c.friendId LIKE :friendId")
                    .setParameter("myId", myId)
                    .setParameter("friendId", friendId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            f = (Friends) em.createQuery("SELECT c FROM model.Friends c WHERE c.myID LIKE :myId AND c.friendId LIKE :friendId")
                    .setParameter("myId", friendId)
                    .setParameter("friendId", myId)
                    .getSingleResult();
        } finally {
            em.close();
            emf.close();
        }

    }
}
