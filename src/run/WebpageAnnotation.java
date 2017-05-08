package run;

import Business.Features;
import Business.InitializeData;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Friends;
import ui.UI;

/**
 *
 * @author Shiyao Zhang
 */
public class WebpageAnnotation {

    private static final String PERSISTENCE_UNIT = "cse210Connector";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Features f = new Features(PERSISTENCE_UNIT);
        InitializeData i = new InitializeData(PERSISTENCE_UNIT);
//        Test t = new Test("testJPA");
        UI ui = new UI(f, i);
        ui.agile();
//        ui.myDetailInformation();
//        ui.allMyFriendsDetailInformation();
//        ui.allMyWebpages();
    }

    private void addData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("123456", "1405896");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    private void friendsAddTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("1405896", "000");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }
}
