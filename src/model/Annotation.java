package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A JPA Entity class of one web annotation. All required information for an
 * annotation would be stored in this class.
 *
 * @author Shiyao Zhang
 */
@Entity(name = "ANNOTATION")
public class Annotation implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    /**
     * Id number of the {@link Person Person} who tags this
     * <code>Annotation</code>.
     */
    @Id
    @Column(nullable = false, unique = false)
    private String taggerID;

    /**
     * The tag published in this <code>Annotation</code>.
     */
    @Id
    @Column(nullable = false, unique = false)
    private String tag;

    /**
     * The web page address this <code>Annotation</code> corresponding to.
     */
    @Id
    @Column(nullable = false, unique = false)
    private String webPage;

    /**
     * Id number of the {@link Person Person} who shared this web page.
     */
    @Id
    @Column(nullable = false, unique = false)
    private String ownerID;

    /**
     * The datetime this <code>Annotation</code> published.
     */
    @Id
    @Column(nullable = false, unique = false)
    private Timestamp datetime;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructs an empty <code>Annotation</code>.
     */
    public Annotation() {
    }

    /**
     * Constructs an <code>Annotation</code> with all attributes.
     * {@link #datetime Datetime} would be assigned by the current time.
     *
     * @param ti Id number of the {@link Person Person} who tags this
     * <code>Annotation</code>.
     * @param t The tag published in this <code>Annotation</code>.
     * @param w The web page address this <code>Annotation</code> corresponding
     * to.
     * @param o Id number of the {@link Person Person} who shared this web page.
     */
    public Annotation(String ti, String t, String w, String o) {
        taggerID = ti;
        tag = t;
        webPage = w;
        ownerID = o;
        Date d = new Date();
        datetime = new Timestamp(d.getTime());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Get the id of tagger of this <code>Annotation</code>.
     *
     * @return id of tagger of this <code>Annotation</code>.
     */
    public String getTaggerID() {
        return taggerID;
    }

    /**
     * Set the id of tagger of this <code>Annotation</code>.
     *
     * @param ti id of tagger of this <code>Annotation</code>.
     */
    public void setTaggerID(String ti) {
        taggerID = ti;
    }

    /**
     * Get the tag of this <code>Annotation</code>.
     *
     * @return tag of this <code>Annotation</code>.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the tag of this <code>Annotation</code>.
     *
     * @param t tag of this <code>Annotation</code>.
     */
    public void setTag(String t) {
        tag = t;
    }

    /**
     * Get the web page this <code>Annotation</code> corresponding to.
     *
     * @return web page address of this <code>Annotation</code>.
     */
    public String getWebPage() {
        return webPage;
    }

    /**
     * Set the web page this <code>Annotation</code> corresponding to.
     *
     * @param w web page address of this <code>Annotation</code>.
     */
    public void setWebPage(String w) {
        webPage = w;
    }

    /**
     * Get id of owner of this <code>Annotation</code>.
     *
     * @return id of owner of this <code>Annotation</code>.
     */
    public String getOwnerID() {
        return ownerID;
    }

    /**
     * Set id of owner of this <code>Annotation</code>.
     *
     * @param o id of owner of this <code>Annotation</code>.
     */
    public void setOwnerID(String o) {
        ownerID = o;
    }

    /**
     * Get the datetime this <code>Annotation</code> published.
     *
     * @return the datetime.
     */
    public Timestamp getDatetime() {
        return datetime;
    }

    /**
     * Set the datetime this <code>Annotation</code> published.
     *
     * @param d the datetime.
     */
    public void setDatetime(Timestamp d) {
        datetime = d;
    }
    // </editor-fold>

}
