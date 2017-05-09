package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A JPA Entity class of person storing records of person. The corresponding
 * table in database is <code>PERSON</code>. Besides, a helper enumerate
 * {@link COURSES COURSES} is defined to restrict possible courses.
 *
 * @author Shiyao Zhang
 */
@Entity(name = "PERSON")
public class Person implements Serializable {

    /**
     * All possible courses of {@link #course course}. {@link #NULL NULL}
     * suggests the {@link #course course} in {@link Person Person} is
     * undefined.
     */
    public static enum COURSES {
        NULL,
        ICS,
        CST,
        DMT
    }

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    /**
     * University student ID. The primary key.
     */
    @Id
    @Column(nullable = false)
    private String ID;

    /**
     * Name of a person.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * A <code>Person</code>'s course which should be one of
     * {@link COURSES COURSES}.
     */
    @Column(nullable = false)
    private String course;

    /**
     * Email of a person.
     */
    @Column(nullable = false, unique = true)
    private String email;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructs an empty <code>Person</code>.
     */
    public Person() {
    }

    /**
     * Constructs a <code>Person</code> with all attributes.
     *
     * @param i ID of the person.
     * @param n name of the person.
     * @param c course of the person.
     * @param e email of the person.
     */
    public Person(String i, String n, COURSES c, String e) {
        ID = i;
        name = n;
        course = c.toString();
        email = e;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Set ID of the <code>Person</code>.
     *
     * @param i the ID to be set.
     */
    public void setID(String i) {
        ID = i;
    }

    /**
     * Get ID of the <code>Person</code>.
     *
     * @return ID of the <code>Person</code>.
     */
    public String getID() {
        return ID;
    }

    /**
     * Set name of the <code>Person</code>.
     *
     * @param n name of the the <code>Person</code>.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Get name of the <code>Person</code>.
     *
     * @return name of the <code>Person</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Set course of the <code>Person</code>.
     *
     * @param c course of the <code>Person</code>.
     */
    public void setCourse(COURSES c) {
        course = c.toString();
    }

    /**
     * Get course of the <code>Person</code>.
     *
     * @return {@link COURSES#NULL NULL} if course is undefined.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Set email of the <code>Person</code>.
     *
     * @param e email of the <code>Person</code>.
     */
    public void setEmail(String e) {
        email = e;
    }

    /**
     * Get email of the <code>Person</code>.
     *
     * @return email of the <code>Person</code>.
     */
    public String getEmail() {
        return email;
    }
    // </editor-fold>

}
