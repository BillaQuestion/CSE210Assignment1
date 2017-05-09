package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A JPA Entity class of one friend relationship. The corresponding table in
 * database is <code>FRIENDS</code>. Id numbers of two {@link Person Person} in
 * this friendship would be stored in this class.
 *
 * @author Shiyao Zhang
 */
@Entity(name = "FRIENDS")
public class Friends implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    /**
     * Id number of the first {@link Person Person} in the friendship.
     */
    @Id
    @Column(nullable = false, unique = false)
    private String myId;

    /**
     * Id number of the second {@link Person Person} in the friendship.
     */
    @Id
    @Column(nullable = false, unique = false)
    private String friendId;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructs an empty <code>Friends</code>.
     */
    public Friends() {
    }

    /**
     * Constructs a <code>Friends</code> object.
     *
     * @param m Id of the first {@link Person Person} in the friendship.
     * @param f Id of the second {@link Person Person} in the friendship.
     */
    public Friends(String m, String f) {
        myId = m;
        friendId = f;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Set id of the first {@link Person Person}.
     *
     * @param m Id of the first {@link Person Person}.
     */
    public void setMyID(String m) {
        myId = m;
    }

    /**
     * Get id of the first {@link Person Person}.
     *
     * @return Id of the first {@link Person Person}.
     */
    public String getMyID() {
        return myId;
    }

    /**
     * Set id of the second {@link Person Person}.
     *
     * @param f Id of the second {@link Person Person}.
     */
    public void setFriendID(String f) {
        friendId = f;
    }

    /**
     * Get id of the second {@link Person Person}.
     *
     * @return Id of the second {@link Person Person}.
     */
    public String getFriendID() {
        return friendId;
    }
    // </editor-fold>

}
