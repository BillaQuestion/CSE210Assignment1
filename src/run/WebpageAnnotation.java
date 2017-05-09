package run;

import Business.Features;
import Business.InitializeData;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Annotation;
import model.Friends;
import ui.UI;

/**
 *
 * @author Shiyao Zhang
 */
public class WebpageAnnotation {

    private static final String PERSISTENCE_UNIT = "cse210Connector";
//    private static final String PERSISTENCE_UNIT = "testJPA";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        addData();
        Features f = new Features(PERSISTENCE_UNIT);
        InitializeData i = new InitializeData(PERSISTENCE_UNIT);
        UI ui = new UI(f, i);
//        ui.agile();
//        ui.removeAnnotation("Good good website", "www.t4.com");
//        ui.allTagsForAWebpageICanSee("www.t4.com", "1405896");
        ui.allTagsForAWebpageICanSee("www.t4.com", "1405052");
//        ui.addAnnotationToAWebpageICanSee("1405052", "www.t3233454.com", "Try again");
//        ui.allAnnotationsForAWebpageICanSee("www.t4.com", "1405052");
//        ui.myDetailInformation();
//        ui.allMyFriendsDetailInformation();
//        ui.allMyWebpages();
//        ui.allFriendsWebsite();
//        ui.allMyTags();
//        ui.addFriends("1405462");
    }

    private static void addData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

//        Friends f = new Friends("123456", "1405896");
//        em.persist(f);
        Annotation a = new Annotation("1405052", "Good good website", "www.t4.com", "1405896");
        a.setDatetime(new Timestamp(10000000));
        em.persist(a);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    private static void friendsAddTest() {
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
