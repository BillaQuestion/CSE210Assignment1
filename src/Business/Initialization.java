/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import model.Annotation;
import model.Friends;
import model.Person;

/**
 *
 * @author Bill
 */
public class Initialization extends Business {

    private static final String MY_NAME = "Shiyao Zhang";
    private static final Person.COURSES MY_COURSE = Person.COURSES.DMT;
    private static final String MY_EMAIL = "Shiyao.Zhang14@student.xjtlu.edu.cn";

    public Initialization(String pu) {
        super(pu);
    }

    //Exception when someone play with you
    public void insertOwnRecord() throws IllegalArgumentException {
//        try {
        Person p = PMGR.find(MY_ID);
        if (p == null) {
            PMGR.add(new Person(MY_ID, MY_NAME, MY_COURSE, MY_EMAIL));
        } else {
            p.setCourse(MY_COURSE);
            p.setEmail(MY_EMAIL);
            p.setID(MY_ID);
            p.setName(MY_NAME);
            PMGR.modify(p);
        }
    }

    public void addFreinds(String fid) {
        try {
            FMGR.tryIsFriend(MY_ID, fid);
            System.out.println("We've alread been friends!");
        } catch (NoResultException nre) {
            FMGR.add(MY_ID, fid);
        }
    }

    public void addAnnotation(Annotation a) throws EntityExistsException {
        AMGR.add(a);
    }
}
