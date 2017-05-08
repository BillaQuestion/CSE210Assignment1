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
public class InitializeData extends Business {

    private static final String MY_NAME = "Shiyao Zhang";
    private static final Person.COURSES MY_COURSE = Person.COURSES.DMT;
    private static final String MY_EMAIL = "Shiyao.Zhang14@student.xjtlu.edu.cn";

    public InitializeData(String pu) {
        super(pu);
    }

    //Exception when someone play with you
    public void addMyRecord() throws IllegalArgumentException {
        addPerson(MY_ID, MY_NAME, MY_COURSE, MY_EMAIL);
    }

    public void addPerson(String id, String name, Person.COURSES course, String email) {
        Person p = PMGR.find(id);
        if (p == null) {
            PMGR.add(new Person(id, name, course, email));
        } else {
            p.setCourse(course);
            p.setEmail(email);
            p.setID(id);
            p.setName(name);
            PMGR.modify(p);
        }
    }

    public void addFreinds(String fid) {
        try {
            FMGR.tryIsFriend(MY_ID, fid);
            System.out.println("We've already been friends!");
        } catch (NoResultException nre) {
            FMGR.add(MY_ID, fid);
        }
    }

    public void addAnnotation(String ti, String t, String w, String o) throws EntityExistsException {
        Annotation a = new Annotation(ti, t, w, o);
        AMGR.add(a);

    }
}
