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
import static test.Test.PERSISTENCE_UNIT;

/**
 *
 * @author Bill
 */
public class PersonJPQLMgr {

    public Person find(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Person p = em.find(Person.class, id);

        em.close();
        emf.close();

        return p;
    }
}
