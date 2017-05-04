/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Business.Features;
import Business.Initialization;
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
    public final String persistence_unit;

    public Test(String pu) {
        features = new Features(pu);
        persistence_unit = pu;
    }

    public void initialization() {
        Initialization i = new Initialization(persistence_unit);
        try {
            i.addFreinds("1405898");
        } catch (RollbackException re) {
            System.out.println("Exception catch.");
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Person p = new Person("1405896", "Shiyao Zhang", Person.COURSES.DMT, "shiyao.zhang14@student.xjtlu.edu.cn");
        em.persist(p);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public void friendsAddTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager em = emf.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();
        userTransaction.begin();

        Friends f = new Friends("1405896", "1405898");
        em.persist(f);

        userTransaction.commit();
        em.close();
        emf.close();
    }

    public void annotationAddTest() {
        try {
            features.addAnnotation("1405896", "testWeb", "testTag");
        } catch (EntityExistsException ee) {
            System.out.println("Exception catch.");
        }
    }

    public void myDetailInformationTest() {
        features.myDetailInformation();
    }

    public void allMyFriendsDetailInformationTest() {
        features.allMyFriendsDetailInformation();
    }

    public void allMyWebpagesTest() {
        features.allMyWebpages();
    }

    public void allMyTagsTest() {
        features.allMyTags();
    }

    public void allFriendsWebsiteTest() {
        features.allFriendsWebsite();
    }
}
