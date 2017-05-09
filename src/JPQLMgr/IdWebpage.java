/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPQLMgr;

import java.util.Objects;

/**
 *
 * @author Bill
 */
public class IdWebpage {

    private String id;
    private String webPage;

    public IdWebpage(String i, String web) {
        id = i;
        webPage = web;
    }

    public String getWebPage() {
        return webPage;
    }

    public String getID() {
        return id;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.webPage);
        return hash;
    }
}
