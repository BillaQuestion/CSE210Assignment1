/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPQLMgr;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Annotation;

/**
 *
 * @author Bill
 */
public class AnnotationJPQLMgr {

    private final String persistence_unit;

    public AnnotationJPQLMgr(String pu) {
        persistence_unit = pu;
    }

    public List<Annotation> find(String oid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :oid")
                .setParameter("oid", oid)
                .getResultList();

        em.close();
        emf.close();

        return la;
    }

    public List<Annotation> findByTagger(String tid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.taggerID LIKE :tid")
                .setParameter("tid", tid)
                .getResultList();

        em.close();
        emf.close();

        return la;
    }

    public List<Annotation> find(String oid, String web) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.website LIKE :web")
                .setParameter("id", oid)
                .setParameter("web", web)
                .getResultList();

        em.close();
        emf.close();

        return la;
    }

    public List<Annotation> find(String oid, String web, String tag) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.website LIKE :web AND c.tag LIKE :t")
                .setParameter("id", oid)
                .setParameter("web", web)
                .setParameter("t", tag)
                .getResultList();

        em.close();
        emf.close();

        return la;
    }

    public List<Annotation> find(String oid, String web, String tag, String tid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.website LIKE :web AND c.tag LIKE :t AND c.taggerId LIKE :tid")
                .setParameter("id", oid)
                .setParameter("web", web)
                .setParameter("t", tag)
                .setParameter("tid", tid)
                .getResultList();

        em.close();
        emf.close();

        return la;
    }

    public void add(Annotation a) {
        if (!isIdentity(a)) {
            throw new EntityExistsException();
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        em.persist(a);

        em.close();
        emf.close();
    }

    public void remove(Annotation a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        em.remove(a);

        em.close();
        emf.close();
    }

    private boolean isIdentity(Annotation a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.website LIKE :web AND c.taggerID LIKE :tid AND c.tag LIKE :t")
                .setParameter("id", a.getOwnerID())
                .setParameter("web", a.getWebPage())
                .setParameter("tid", a.getTaggerID())
                .setParameter("t", a.getTag())
                .getResultList();

        em.close();
        emf.close();

        if (la.isEmpty()) {
            return true;
        }
        return false;
    }
}
