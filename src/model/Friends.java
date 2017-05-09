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
//@IdClass(FriendsId.class)
public class Friends implements Serializable {

    @Id
    @Column(nullable = false, unique = false)
    private String myId;

    @Id
    @Column(nullable = false, unique = false)
    private String friendId;

    public Friends() {
    }

    public Friends(String m, String f) {
        myId = m;
        friendId = f;
    }

    public void setMyID(String m) {
        myId = m;
    }

    public String getMyID() {
        return myId;
    }

    public void setFriendID(String f) {
        friendId = f;
    }

    public String getFriendID() {
        return friendId;
    }

//    public class FriendsId implements Serializable {
//
//        private String myId;
//        private String friendId;
//
//        public FriendsId() {
//        }
//
//        public FriendsId(String m, String f) {
//            myId = m;
//            friendId = f;
//        }
//
//        public void setMyID(String m) {
//            myId = m;
//        }
//
//        public String getMyId() {
//            return myId;
//        }
//
//        public void setFriendID(String f) {
//            friendId = f;
//        }
//
//        public String getFriendId() {
//            return friendId;
//        }
//
//        @Override
//        public int hashCode() {
//            return myId.hashCode() + 31 * friendId.hashCode();
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) {
//                return true;
//            }
//            if (obj == null) {
//                return false;
//            }
//            if (getClass() != obj.getClass()) {
//                return false;
//            }
//            FriendsId other = (FriendsId) obj;
//            if (myId == null) {
//                if (other.myId != null) {
//                    return false;
//                }
//            } else if (!myId.equals(other.myId)) {
//                return false;
//            }
//            if (friendId == null) {
//                if (other.friendId != null) {
//                    return false;
//                }
//            } else if (!friendId.equals(other.friendId)) {
//                return false;
//            }
//            return true;
//        }
//    }
}
