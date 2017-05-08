/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Business.Features;
import Business.InitializeData;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import model.Annotation;
import model.Friends;
import model.IdWebpage;
import model.Person;

/**
 *
 * @author Bill
 */
//Try to use Junit
public class UI {

    private final Features features;
    private final InitializeData init;

    public UI(Features f, InitializeData i) {
        features = f;
        init = i;
    }

    public void agile() {
        Set<IdWebpage> lw = features.allFriendsWebsite("12345432");
        System.out.println("All My Friends' Webpages");
        System.out.println("========================");
        for (IdWebpage a : lw) {
            System.out.println(a.getID() + "\t\t" + a.getWebPage());
        }
        System.out.println("========================");
    }

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

    public void allMyTags() {
        List<String> lt = features.allMyTags();
        System.out.println("All My Tags");
        System.out.println("========================");
        while (!lt.isEmpty()) {
            System.out.println(lt.remove(0));
        }
        System.out.println("========================");
    }

    public void allFriendsWebsite() {
        Set<IdWebpage> lw = features.allMyFriendsWebsite();
        System.out.println("All My Friends' Webpages");
        System.out.println("========================");
        for (IdWebpage a : lw) {
            System.out.println(a.getID() + "\t\t" + a.getWebPage());
        }
        System.out.println("========================");
    }

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

    public void removeAnnotation(String tag, String web) {
        features.removeAnnotation(tag, web);
    }

    public void addMyInformation() {
        init.addMyRecord();
    }

    public void initAddFriends(String fid) {
        try {
            init.addFreinds(fid);
        } catch (EntityExistsException eee) {
            System.out.println("exception");
        }
    }

    public void initAddAnnotation(String ti, String t, String w, String o) {
        try {
            init.addAnnotation(ti, t, w, o);
        } catch (RollbackException re) {
            System.out.println("Exception catch.");
        }
    }

    public void initAddPerson(String id, String name, Person.COURSES course, String email) {
        init.addPerson(id, name, course, email);
    }
}
