package Business;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import model.Annotation;
import model.Person;

/**
 * A business layer class Implementing all functions needed to initialize the
 * database. There are constants declared including: my name, my course, and my
 * email.
 *
 * @author Shiyao Zhang
 */
public class InitializeData extends Business {

    /**
     * Constant represents my name.
     */
    private static final String MY_NAME = "Shiyao Zhang";

    /**
     * Constant represents my program.
     */
    private static final Person.COURSES MY_COURSE = Person.COURSES.DMT;

    /**
     * Constant represents my email.
     */
    private static final String MY_EMAIL = "Shiyao.Zhang14@student.xjtlu.edu.cn";

    /**
     * Constructs a <code>InitializeData</code> object initializing Java
     * Persistence Query Language Manager of Person, Friends, and Annotation.
     *
     * @param pu The persistence unit used to connect database.
     */
    public InitializeData(String pu) {
        super(pu);
    }

    //Exception when someone play with you
    /**
     * Initialize my record into database. If the record is already exist, the
     * Entity found would be modified.
     *
     * @throws IllegalArgumentException if my record is removed from the
     * database between program finding my record and modifying the record.
     */
    public void addMyRecord() throws IllegalArgumentException {
        addPerson(MY_ID, MY_NAME, MY_COURSE, MY_EMAIL);
    }

    /**
     * Initialize a person with all information. If the record is already exist,
     * the Entity found would be modified.
     *
     * @param id Id of the Person.
     * @param name Name of the Person.
     * @param course Program of the Person.
     * @param email Email of the Person.
     */
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

    /**
     * Initialize a <code>Friends</code> into the database. "We've already been
     * friends!" may be printed on command window if the specific person has
     * already been my friend.
     *
     * @param fid Id of my friend.
     */
    public void addFreinds(String fid) {
        try {
            FMGR.tryFriend(MY_ID, fid);
            System.out.println("We've already been friends!");
        } catch (NoResultException nre) {
            FMGR.add(MY_ID, fid);
        }
    }

    /**
     * Initialize an Annotation into the database. The Annotation to be added
     * could not be duplicated with any Entity which has already been in the
     * database. An Annotation would be duplicated with another if and only if
     * all information of Annotations exclude datetime satisfy
     * <code>String.equals()</code>.
     *
     * @param ti Tagger id.
     * @param t Tag.
     * @param w Web page address.
     * @param o Owner id.
     * @throws EntityExistsException if there exists an Entity has exactly same
     * informations exclude datetime with these specific informations.
     */
    public void addAnnotation(String ti, String t, String w, String o) throws EntityExistsException {
        Annotation a = new Annotation(ti, t, w, o);
        AMGR.add(a);
    }
}
