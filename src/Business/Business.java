/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import JPQLMgr.AnnotationJPQLMgr;
import JPQLMgr.FriendsJPQLMgr;
import JPQLMgr.PersonJPQLMgr;

/**
 *
 * @author Bill
 */
public abstract class Business {

    protected final PersonJPQLMgr PMGR;
    protected final FriendsJPQLMgr FMGR;
    protected final AnnotationJPQLMgr AMGR;
    protected static final String MY_ID = "1405896";

    public Business(String pu) {
        PMGR = new PersonJPQLMgr(pu);
        FMGR = new FriendsJPQLMgr(pu);
        AMGR = new AnnotationJPQLMgr(pu);
    }
}
