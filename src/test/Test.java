/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Business.Features;
import Business.Initialization;
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
public class Test {

    private final Features features;
    private final Initialization init;
    public final String persistence_unit;

    public Test(String pu) {
        features = new Features(pu);
        persistence_unit = pu;
        init = new Initialization(pu);
    }

    public void initialization() {
        try {
            init.addFreinds("1234");
//            Annotation a = new Annotation("1405896","test","test.com","1405896");
//            init.addAnnotation(a);
//        } catch (RollbackException re) {
//            System.out.println("Exception catch.");
        } catch (EntityExistsException eee) {
            System.out.println("exception");
        }
    }

    public void addData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("1405896", "1405897");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public void personAddTest() {
        init.insertOwnRecord();
    }

    public void friendsAddTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("1405896", "000");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public void myDetailInformation() {
        Person p = features.myDetailInformation();
        System.out.println("My Detail Information");
        System.out.println("========================");
        System.out.println(p.getID());
        System.out.println(p.getName());
        System.out.println(p.getCourse());
        System.out.println(p.getEmail());
        System.out.println("========================");
    }

    public void allMyFriendsDetailInformation() {
        List<Person> lp = features.allMyFriendsDetailInformation();
        System.out.println("All My Friends' Detail Information");
        while (!lp.isEmpty()) {
            Person p = lp.remove(0);
            System.out.println("========================");
            System.out.println(p.getID());
            System.out.println(p.getName());
            System.out.println(p.getCourse());
            System.out.println(p.getEmail());
            System.out.println("========================");
        }
    }

    public void allMyWebpages() {
        List<String> lw = features.allMyWebpages();
        System.out.println("All My Webpages");
        System.out.println("========================");
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
            System.out.println(a.getDatetime().toString());
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

    private void addMyInformation() {
        init.insertOwnRecord();
    }

    private void addFriends(String fid) {
        init.addFreinds(fid);
    }

    private void addAnnotation(Annotation a) {
        init.addAnnotation(a);
    }
}
