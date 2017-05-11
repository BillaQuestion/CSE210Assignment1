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
        ui.removeAnnotation("Good good website", "dfasdfasdfasdfasdfas");
//        ui.allTagsForAWebpageICanSee("www.t4.com", "1405896");
//        ui.allTagsForAWebpageICanSee("www.t4.com", "1405052");
//        ui.addAnnotationToAWebpageICanSee("1405052", "www.t3233454.com", "Try again");
//        ui.allAnnotationsForAWebpageICanSee("www.t4.com", "1405052");
//        ui.myDetailInformation();
//        ui.allMyFriendsDetailInformation();
//        ui.allMyWebpages();
//        ui.allFriendsWebsite();
//        ui.allMyTags();
//        ui.addFriends("1405462");
    }
}
