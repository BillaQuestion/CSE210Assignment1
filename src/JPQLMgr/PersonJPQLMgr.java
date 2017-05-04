/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPQLMgr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Person;

/**
 *
 * @author Bill
 */
public class PersonJPQLMgr {

    private final String persistence_unit;

    public PersonJPQLMgr(String pu) {
        persistence_unit = pu;
    }

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
