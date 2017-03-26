package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Shiyao Zhang
 */
@Entity(name = "ANNOTATION")
public class Annotation implements Serializable {

//    @Id
//    private String uUID;
    @Id
    @Column(nullable = false, unique = false)
    private String taggerID;

    @Id
    @Column(nullable = false, unique = false)
    private String tag;

    @Id
    @Column(nullable = false, unique = false)
    private String webPage;

    @Id
    @Column(nullable = false, unique = false)
    private String ownerID;

    @Id
    @Column(nullable = false, unique = false)
    private Timestamp datetime;

    public Annotation() {
//        uUID = UUID.randomUUID().toString();
    }

    public Annotation(String ti, String t, String w, String o) {
        taggerID = ti;
        tag = t;
        webPage = w;
        ownerID = o;
        Date d = new Date();
        datetime = new Timestamp(d.getTime());
//        uUID = UUID.randomUUID().toString();
    }

    public String getTaggerID() {
        return taggerID;
    }

    public void setTaggerID(String ti) {
        taggerID = ti;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String t) {
        tag = t;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String w) {
        webPage = w;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String o) {
        ownerID = o;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp d) {
        datetime = d;
    }
}
