package Business;

import JPQLMgr.AnnotationJPQLMgr;
import JPQLMgr.FriendsJPQLMgr;
import JPQLMgr.PersonJPQLMgr;

/**
 * Class describe business logic of the coursework. A constant
 * <code>MY_ID</code> is defined as <code>"1405896"</code> which is id of
 * author.
 *
 * @author Shiyao Zhang
 */
public abstract class Business {

    /**
     * Java Persistence Query Language Manager of Person.
     */
    protected final PersonJPQLMgr PMGR;

    /**
     * Java Persistence Query Language Manager of Friends.
     */
    protected final FriendsJPQLMgr FMGR;

    /**
     * Java Persistence Query Language Manager of Annotation.
     */
    protected final AnnotationJPQLMgr AMGR;

    /**
     * Id of author.
     */
    protected static final String MY_ID = "1405896";

    /**
     * Constructs a <code>Business</code> object initializing Java Persistence
     * Query Language Manager of Person, Friends, and Annotation.
     *
     * @param pu The persistence unit used to connect database.
     */
    public Business(String pu) {
        PMGR = new PersonJPQLMgr(pu);
        FMGR = new FriendsJPQLMgr(pu);
        AMGR = new AnnotationJPQLMgr(pu);
    }
}
