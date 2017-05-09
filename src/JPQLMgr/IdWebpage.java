package JPQLMgr;

import java.util.Objects;

/**
 * Represents an "id - web page" pair. <code>IdWebpage</code> is mainly used to
 * send data between methods when "id - web page" pair identity required.
 * Methods {@link #equals(java.lang.Object) equals} and
 * {@link #hashCode() hashCode} are override.
 *
 * @author Shiyao Zhang
 */
public class IdWebpage {

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    /**
     * Id in an "id - web page" pair.
     */
    private String id;

    /**
     * Web page address in an "id - web page" pair.
     */
    private String webPage;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructs an "id - web page" pair with all information.
     *
     * @param i Id in the pair
     * @param web web page address in the pair
     */
    public IdWebpage(String i, String web) {
        id = i;
        webPage = web;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Get the web page address.
     *
     * @return web page address
     */
    public String getWebPage() {
        return webPage;
    }

    /**
     * Get the id.
     *
     * @return id
     */
    public String getID() {
        return id;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return <code>trud</code> if and only if <code>id</code> and
     * <code>webPage</code> in two <code>Object</code>s are "equal to".
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!IdWebpage.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final IdWebpage other = (IdWebpage) obj;
        if (!this.id.equals(other.getID()) || !this.webPage.equals(other.getWebPage())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.webPage);
        return hash;
    }
    // </editor-fold>

}
