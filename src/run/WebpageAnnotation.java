package run;

import test.Test;

/**
 *
 * @author Shiyao Zhang
 */
public class WebpageAnnotation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test t = new Test("testJPA");
//        Test t = new Test("cse210Connector");
//        t.annotationAddTest();
//        t.initialization();
//        t.friendsAddTest();

//        Test.addData();

        t.personAddTest();
//        Test.friendsAddTest();
//        Test.myDetailInformationTest();
//        Test.allMyFriendsDetailInformationTest();
//        Test.annotationAddTest();
//        Test.allMyWebpagesTest();
//Test.allMyTagsTest();
//Test.allFriendsWebsiteTest();

    }

}
