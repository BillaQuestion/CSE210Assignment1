package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Shiyao Zhang
 */
@Entity(name = "FRIENDS")
public class Friends implements Serializable {

//    @Id
//    private String uUID;

    @Id
    @Column(nullable = false, unique = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String myID;

    @Id
    @Column(nullable = false, unique = false)
    private String friendID;

    public Friends() {
//        uUID = UUID.randomUUID().toString();
    }

    public Friends(String m, String f) {
        myID = m;
        friendID = f;
//        uUID = UUID.randomUUID().toString();
    }

    public void setMyID(String m) {
        myID = m;
    }

    public String getMyID() {
        return myID;
    }

    public void setFriendID(String f) {
        friendID = f;
    }

    public String getFriendID() {
        return friendID;
    }

//    public void setUUID(UUID u) {
//        uUID = u.toString();
//    }
//
//    public String getUUID() {
//        return uUID;
//    }
}
