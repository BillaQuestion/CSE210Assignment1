/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Business.Features;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Annotation;
import model.Friends;
import model.Person;

/**
 *
 * @author Bill
 */
public class Test {

    private static final Features F = new Features();
    public static String PERSISTENCE_UNIT = "testJPA";
//    public static String PERSISTENCE_UNIT = "cse210Connector";

    public static void addData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("1405896", "1405897");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public static void personAddTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Person p = new Person("1405896", "Shiyao Zhang", Person.COURSES.DMT, "shiyao.zhang14@student.xjtlu.edu.cn");
        em.persist(p);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public static void friendsAddTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("1405896", "1405898");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public static void annotationAddTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Annotation a = new Annotation("1405896", "tag", "xjtlu.edu.cn", "1405896");
        em.persist(a);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public static void myDetailInformationTest() {
        F.myDetailInformation();
    }

    public static void allMyFriendsDetailInformationTest() {
        F.allMyFriendsDetailInformation();
    }

    public static void allMyWebpagesTest() {
        F.allMyWebpages();
    }

    public static void allMyTagsTest() {
        F.allMyTags();
    }

    public static void allFriendsWebsiteTest() {
        F.allFriendsWebsite();
    }
}
