/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import model.Annotation;
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
        try {
            PMGR.add(new Person(MY_ID, MY_NAME, MY_COURSE, MY_EMAIL));
        } catch (RollbackException re) {
            Person p = PMGR.find(MY_ID);
            p.setCourse(MY_COURSE);
            p.setEmail(MY_EMAIL);
            p.setID(MY_ID);
            p.setName(MY_ID);
            PMGR.modify(p);
        }
    }
    
    public void addFreinds(String fid) throws RollbackException {
        FMGR.add(MY_ID, fid);
    }
    
    public void addAnnotation(Annotation a) throws EntityExistsException {
        AMGR.add(a);
    }
}
