/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Business.Features;
import Business.InitializeData;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import model.Annotation;
import model.Friends;
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
        List<Person> lp = features.allFriendsDetailInformation("12345678901");
        System.out.println("All My Friends' Detail Information");
        System.out.println("========================");
        if (lp.isEmpty()) {
            System.out.println("I don't have a friend.");
            System.out.println("Maybe friends forgot adding double direction relationship.");
            System.out.println("========================");

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
        List<String> lw = features.allMyWebpages();
        System.out.println("All My Webpages");
        System.out.println("========================");
        if (lw.isEmpty()) {
            System.out.println("No Webpage Published.");
        }
        while (!lw.isEmpty()) {
            System.out.println(lw.remove(0));
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
        List<String> lw = features.allFriendsWebsite();
        System.out.println("All My Friends' Webpages");
        System.out.println("========================");
        while (!lw.isEmpty()) {
            System.out.println(lw.remove(0));
        }
        System.out.println("========================");
    }

    public void allTagsForAWebpageICanSee(String webpage, String oid) {
        List<String> lt = features.allTagsForAWebsitePublishedByFriendOrMyself(webpage, oid);
        System.out.println("All Tags I can See for This Webpage");
        System.out.println("========================");
        while (!lt.isEmpty()) {
            System.out.println(lt.remove(0));
        }
        System.out.println("========================");
    }

    public void allAnnotationsForAWebpageICanSee(String web, String oid) {
        List<Annotation> la = features.allDatetimeSortedAnnotationForAWebsitePublishedByFriendOrMyself(web, oid);
        System.out.println("All Annotations I can See for This Webpage");
        while (!la.isEmpty()) {
            System.out.println("========================");
            Annotation a = la.remove(0);
            System.out.println("Datetime: " + a.getDatetime().toString());
            System.out.println(a.getOwnerID());
            System.out.println(a.getWebPage());
            System.out.println(a.getTaggerID());
            System.out.println(a.getTag());
        }
        System.out.println("========================");
    }

    public void addAnnotationToAWebpageICanSee(String oid, String web, String tag) {
        try {
            features.addAnnotation(oid, web, tag);
        } catch (EntityExistsException ee) {
            System.out.println("Exception catch.");
        }
    }

    public void removeAnnotation(String tag, String web) {
        features.removeAnnotation(tag, web);
    }

    public void addMyInformation() {
        init.addMyRecord();
    }

    public void addFriends(String fid) {
        try {
            init.addFreinds(fid);
        } catch (EntityExistsException eee) {
            System.out.println("exception");
        }
    }

    public void addAnnotation(Annotation a) {
        try {
            init.addAnnotation(a);
        } catch (RollbackException re) {
            System.out.println("Exception catch.");
        }
    }

    public void addPerson(String id, String name, Person.COURSES course, String email) {
        init.addPerson(id, name, course, email);
    }
}
