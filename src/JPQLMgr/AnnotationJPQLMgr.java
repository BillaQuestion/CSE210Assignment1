/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPQLMgr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Annotation;
import model.IdWebpage;

/**
 *
 * @author Bill
 */
public class AnnotationJPQLMgr {

    private final String persistence_unit;

    public AnnotationJPQLMgr(String pu) {
        persistence_unit = pu;
    }

    /**
     * *
     * Find all annotation where ownerID == taggerID.
     *
     * @param oid ownerID
     * @return
     */
    public Set<IdWebpage> findWebpageWithId(String oid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :oid AND c.taggerID LIKE :tid")
                .setParameter("oid", oid)
                .setParameter("tid", oid)
                .getResultList();

        userTransaction.commit();
        em.close();
        emf.close();

        Set<IdWebpage> si = new HashSet();
        for (Annotation a : la) {
            si.add(new IdWebpage(a.getOwnerID(), a.getWebPage()));
        }
        return si;
    }

    public List<Annotation> findByTagger(String tid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.taggerID LIKE :tid")
                .setParameter("tid", tid)
                .getResultList();

        userTransaction.commit();
        em.close();
        emf.close();

        return la;
    }

    public List<Annotation> find(String oid, String web) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.webPage LIKE :web")
                .setParameter("id", oid)
                .setParameter("web", web)
                .getResultList();

        userTransaction.commit();
        em.close();
        emf.close();

        return la;
    }

    public List<Annotation> find(String oid, String web, String tag) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.webPage LIKE :web AND c.tag LIKE :t")
                .setParameter("id", oid)
                .setParameter("web", web)
                .setParameter("t", tag)
                .getResultList();

        userTransaction.commit();
        em.close();
        emf.close();

        return la;
    }

    //not used
    public Annotation find(String oid, String web, String tag, String tid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Annotation la = (Annotation) em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.website LIKE :web AND c.tag LIKE :t AND c.taggerId LIKE :tid")
                .setParameter("id", oid)
                .setParameter("web", web)
                .setParameter("t", tag)
                .setParameter("tid", tid)
                .getSingleResult();

        userTransaction.commit();
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

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public void remove(Annotation a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        a = em.merge(a);
        em.remove(a);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public void remove(String oid, String web, String tag, String tid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        em.createQuery("DELETE FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.webPage LIKE :web AND c.tag LIKE :t AND c.taggerID LIKE :tid")
                .setParameter("id", oid)
                .setParameter("web", web)
                .setParameter("t", tag)
                .setParameter("tid", tid)
                .executeUpdate();

        userTransaction.commit();
        em.close();
        emf.close();
    }

    private boolean isIdentity(Annotation a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        List<Annotation> la = em.createQuery("SELECT c FROM model.Annotation c WHERE c.ownerID LIKE :id AND c.webPage LIKE :web AND c.taggerID LIKE :tid AND c.tag LIKE :t")
                .setParameter("id", a.getOwnerID())
                .setParameter("web", a.getWebPage())
                .setParameter("tid", a.getTaggerID())
                .setParameter("t", a.getTag())
                .getResultList();

        userTransaction.commit();
        em.close();
        emf.close();

        if (la.isEmpty()) {
            return true;
        }
        return false;
    }
}
