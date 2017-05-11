package ui;

import Business.Features;
import Business.InitializeData;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import model.Annotation;
import JPQLMgr.IdWebpage;
import model.Person;

/**
 * Describe all functions may be used for user interface whenever during
 * demonstration or initialization. Command window would be used as information
 * output.
 *
 * @author Shiyao Zhang
 */
//Try to use Junit
public class UI {

    /**
     * Object describes required features.
     */
    private final Features features;

    /**
     * Object describes needed initialization methods.
     */
    private final InitializeData init;

    /**
     * Constructs an <code>UI</code> object.
     *
     * @param f Object Object describes required features.
     * @param i Object describes needed initialization methods.
     */
    public UI(Features f, InitializeData i) {
        features = f;
        init = i;
    }

    /**
     * Show all my information.
     */
    public void myDetailInformation() {
        Person p = features.myDetailInformation();
        if (p == null) {
            System.out.println("My detail information is not in the database!");
        } else {
            System.out.println("My Detail Information");
            System.out.println("========================");
            System.out.println("ID: " + p.getID());
            System.out.println("Name: " + p.getName());
            System.out.println("Program: " + p.getCourse());
            System.out.println("Email: " + p.getEmail());
            System.out.println("========================");
        }
    }

    /**
     * Show all my friends information.
     */
    public void allMyFriendsDetailInformation() {
        List<Person> lp = features.allMyFriendsDetailInformation();
        System.out.println("All My Friends' Detail Information");
        System.out.println("========================");
        if (lp.isEmpty()) {
            System.out.println("I don't have a friend.");
            System.out.println("Maybe friends forgot add double direction relationship.");
        }
        while (!lp.isEmpty()) {
            Person p = lp.remove(0);
            System.out.println("ID: " + p.getID());
            System.out.println("Name: " + p.getName());
            System.out.println("Program: " + p.getCourse());
            System.out.println("Email: " + p.getEmail());
            System.out.println("========================");
        }
    }

    /**
     * Show all web pages published by myself.
     */
    public void allMyWebpages() {
        Set<IdWebpage> sw = features.allMyWebpages();
        System.out.println("All My Webpages");
        System.out.println("========================");
        if (sw.isEmpty()) {
            System.out.println("No Webpage Published.");
        }
        for (IdWebpage a : sw) {
            System.out.println(a.getWebPage());
        }
        System.out.println("========================");
    }

    /**
     * Show all tags published by myself.
     */
    public void allMyTags() {
        List<String> lt = features.allMyTags();
        System.out.println("All My Tags");
        System.out.println("========================");
        while (!lt.isEmpty()) {
            System.out.println(lt.remove(0));
        }
        System.out.println("========================");
    }

    /**
     * Show all web pages published by all of my friends.
     */
    public void allFriendsWebsite() {
        Set<IdWebpage> lw = features.allMyFriendsWebsite();
        System.out.println("All My Friends' Webpages");
        System.out.println("========================");
        for (IdWebpage a : lw) {
            System.out.println(a.getID() + "\t\t" + a.getWebPage());
        }
        System.out.println("========================");
    }

    /**
     * Show all tags published by my friends on a specific web page. The
     * specific web page must be published by one of my friends or myself.
     *
     * @param webpage Web page address.
     * @param oid Owner id of the web page.
     */
    public void allTagsForAWebpageICanSee(String webpage, String oid) {
        try {
            List<String> lt = features.allTagsForAWebsitePublishedByFriendOrMyself(webpage, oid);
            System.out.println("All Tags for This Webpage");
            System.out.println("========================");
            if (lt.isEmpty()) {
                System.out.println("Webpage does not exist.");
            }
            while (!lt.isEmpty()) {
                System.out.println(lt.remove(0));
            }
        } catch (NoResultException nre) {
            System.out.println("All Tags for This Webpage");
            System.out.println("========================");
            System.out.println("You cannot see website created by a stranger.");
        } finally {
            System.out.println("========================");
        }
    }

    /**
     * Show all Annotations published for a specific web page. The specific web
     * page must be published by one of my friends or myself.
     *
     * @param web Web page address.
     * @param oid Owner id of the web page.
     */
    public void allAnnotationsForAWebpageICanSee(String web, String oid) {
        try {
            List<Annotation> la = features.allDatetimeSortedAnnotationForAWebsitePublishedByFriendOrMyself(web, oid);
            System.out.println("All Annotations I can See for This Webpage");
            System.out.println("========================");
            if (la.isEmpty()) {
                System.out.println("Webpage does not exist.");
                System.out.println("========================");
            }
            while (!la.isEmpty()) {
                Annotation a = la.remove(0);
                System.out.println("Datetime: " + a.getDatetime().toString());
                System.out.println("Owner ID: " + a.getOwnerID());
                System.out.println("Webpage: " + a.getWebPage());
                System.out.println("Tagger ID: " + a.getTaggerID());
                System.out.println("Tag: " + a.getTag());
                System.out.println("========================");
            }
        } catch (NoResultException nre) {
            System.out.println("All Annotations I can See for This Webpage");
            System.out.println("========================");
            System.out.println("You cannot see website created by a stranger.");
            System.out.println("========================");
        }
    }

    /**
     * Add an Annotation to a web page published by one of my friends or myself.
     *
     * @param oid Owner id of the web page.
     * @param web Web page address.
     * @param tag Tag.
     */
    public void addAnnotationToAWebpageICanSee(String oid, String web, String tag) {
        try {
            Annotation a = features.addAnnotation(oid, web, tag);
            System.out.println("Add Annotation");
            System.out.println("========================");
            System.out.println("Datetime: " + a.getDatetime().toString());
            System.out.println("Owner ID: " + a.getOwnerID());
            System.out.println("Webpage: " + a.getWebPage());
            System.out.println("Tagger ID: " + a.getTaggerID());
            System.out.println("Tag: " + a.getTag());
            System.out.println("========================");
        } catch (EntityExistsException ee) {
            System.out.println("Add Annotation");
            System.out.println("========================");
            System.out.println("Same tag has already been given.");
            System.out.println("========================");
        } catch (EntityNotFoundException enf) {
            System.out.println("Add Annotation");
            System.out.println("========================");
            System.out.println("Webpage does not exist.");
            System.out.println("========================");
        }
    }

    /**
     * Remove Annotations for a specific tag on a specific web page. If the web
     * page is published by myself, all Annotations with the specific tag would
     * be removed. If the web page is published by my friends, only Annotations
     * published by myself with the specific tag would be removed.
     *
     * @param tag Tag of the Annotation.
     * @param web Web page address.
     */
    public void removeAnnotation(String tag, String web) {
        features.removeAnnotation(tag, web);
    }

    /**
     * Initialize my information to the database. If the record is already
     * exist, the Entity found would be modified.
     */
    public void initMyInformation() {
        init.addMyRecord();
    }

    /**
     * Initialize a <code>Friends</code> in to the database.
     *
     * @param fid Id of the friend.
     */
    public void initAddFriends(String fid) {
        try {
            init.addFreinds(fid);
        } catch (EntityExistsException eee) {
            System.out.println("We've already been friends!");
        }
    }

    /**
     * Initialize an Annotation to the database.
     *
     * @param ti Tagger id.
     * @param t Tag.
     * @param w Web page address.
     * @param o Owner id.
     */
    public void initAddAnnotation(String ti, String t, String w, String o) {
        try {
            init.addAnnotation(ti, t, w, o);
        } catch (RollbackException re) {
            System.out.println("This Annotation has already been added.");
        }
    }

    /**
     * Initialize a Person to the database.
     *
     * @param id Id of the Person.
     * @param name Name of the Person.
     * @param course Program of the Person.
     * @param email Email of the Person.
     */
    public void initAddPerson(String id, String name, Person.COURSES course, String email) {
        init.addPerson(id, name, course, email);
    }
}
