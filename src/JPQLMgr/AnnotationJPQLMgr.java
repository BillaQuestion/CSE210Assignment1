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

/**
 * A Java Persistence Query Language Manager which would send query for
 * {@link Annotation Annotation} to the connected database. There are methods to
 * find, add, and remove {@link Annotation Annotation} to/from the database.
 *
 * @author Shiyao Zhang
 */
public class AnnotationJPQLMgr {

    /**
     * The persistence unit used to connect database.
     */
    private final String persistence_unit;

    /**
     * Constructs a JPQL Manager connecting to database.
     *
     * @param pu The persistence unit.
     */
    public AnnotationJPQLMgr(String pu) {
        persistence_unit = pu;
    }

    /**
     * *
     * Find all annotation where <code>ownerID == taggerID</code> from the
     * database.
     *
     * @param oid ownerID
     * @return <code>null</code> if the owner didn't publish any web page.
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

    /**
     * Find Annotation by tagger id from the database.
     *
     * @param tid tagger id.
     * @return <code>null</code> if there is no tag published by this specific
     * id.
     */
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

    /**
     * Find all Annotation published with a specific web page from the database.
     * The specific web page would be satisfied if and only if the owner id and
     * web page are all "equal to" the given value.
     *
     * @param oid Owner id of the web page.
     * @param web Web page address.
     * @return <code>null</code> if no <code>Annotation</code> found.
     */
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

    /**
     * Find all Annotation published with a specific tag from the database. The
     * specific tag would be satisfied if and only if the tag, which is "equal
     * to" the given value, is common on a specific web page, whose values would
     * be "equal to" the web page address and owner id.
     *
     * @param oid Owner id.
     * @param web Web page address.
     * @param tag Tag.
     * @return <code>null</code> if no Annotation of this specific tag.
     */
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

    /**
     * Add an <code>Annotation</code> to the database. The identity of the
     * Entity would checked.
     *
     * @param a The Annotation to be added.
     * @throws EntityExistsException if the Entity to be added is not identity.
     */
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

    /**
     * Remove an <code>Annotation</code> from the database. The
     * <code>Entity</code> would be merged, if there exists, to the same
     * <code>Entity</code> in the database. Hence, the Entity to be removed
     * cannot be transient.
     *
     * @param a The <code>Annotation</code> to be removed.
     */
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

    /**
     * Remove all <code>Annotation</code> with a specific tag. The specific tag
     * would be satisfied if and only if all owner id, web page address, tag,
     * and tagger id are "equal to" values given.
     *
     * @param oid Owner id.
     * @param web Web page address.
     * @param tag Tag.
     * @param tid Tagger id.
     */
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
