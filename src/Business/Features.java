/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import model.Person;
import model.Friends;
import model.Annotation;
import JPQLMgr.PersonJPQLMgr;
import JPQLMgr.FriendsJPQLMgr;
import JPQLMgr.AnnotationJPQLMgr;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

/**
 *
 * @author Bill
 */
public class Features {

    private final PersonJPQLMgr PMGR;
    private final FriendsJPQLMgr FMGR;
    private final AnnotationJPQLMgr AMGR;
    private static final String MYID = "1405896";

    public Features(String pu){
        PMGR = new PersonJPQLMgr(pu);
        FMGR = new FriendsJPQLMgr(pu);
        AMGR = new AnnotationJPQLMgr(pu);
    }
    public Person myDetailInformation() {
        return PMGR.find(MYID);
    }

    public List<Person> allMyFriendsDetailInformation() {
        List<Friends> lf = FMGR.find(MYID);

        List<Person> lp = new ArrayList<>();
        ListIterator<Friends> it = lf.listIterator();
        while (it.hasNext()) {
            String fid = it.next().getFriendID();
            Person p = PMGR.find(fid);
            try {
                lp.add(p);
            } catch (NullPointerException e) {
                //if the friend is not registered, 
                //add a Person with ID only to the return list
                Person np = new Person();
                np.setID(fid);
                lp.add(np);
            }
        }

        return lp;
    }

    public List<String> allMyWebpages() {
        List<Annotation> la = AMGR.find(MYID);

        ArrayList<String> ls = new ArrayList<>();
        ListIterator<Annotation> it = la.listIterator();
        while (it.hasNext()) {
            String web = it.next().getWebPage();
            ls.add(web);
        }

        return ls;
    }

    public List<String> allMyTags() {
        List<Annotation> la = AMGR.findByTagger(MYID);

        ArrayList<String> ls = new ArrayList<>();
        ListIterator<Annotation> it = la.listIterator();
        while (it.hasNext()) {
            String tag = it.next().getTag();
            if (!ls.contains(tag)) {
                ls.add(tag);
            }
        }

        return ls;
    }

    public List<String> allFriendsWebsite() {
        List<Friends> lf = FMGR.find(MYID);

        ArrayList<String> lw = new ArrayList<>();
        ListIterator<Friends> it = lf.listIterator();
        while (it.hasNext()) {
            String fid = it.next().getFriendID();
            List<Annotation> lfa = AMGR.find(fid);
            ListIterator<Annotation> itlfa = lfa.listIterator();
            while (itlfa.hasNext()) {
                String web = itlfa.next().getWebPage();
                if (!lw.contains(web)) {
                    lw.add(web);
                }
            }
        }

        return lw;
    }

    public List<String> allTagsForAWebsitePublishedByFriendOrMyself(String web, String oid) {
        List<Annotation> la = allDatetimeSortedAnnotationForAWebsitePublishedByFriendOrMyself(web, oid);
        ListIterator<Annotation> it = la.listIterator();
        ArrayList<String> lw = new ArrayList<>();
        while (it.hasNext()) {
            String tag = it.next().getTag();
            if (!lw.contains(tag)) {
                lw.add(tag);
            }
        }
        return lw;
    }

    public List<Annotation> allDatetimeSortedAnnotationForAWebsitePublishedByFriendOrMyself(String web, String oid) {
        tryIfICanSeeThisAnnotation(web, oid);
        List<Annotation> la = AMGR.find(oid, web);

        la.sort((Object o1, Object o2) -> {
            Annotation a1 = (Annotation) o1;
            Annotation a2 = (Annotation) o2;
            return a1.getDatetime().toString().compareTo(a2.getDatetime().toString());
        });
        return la;
    }

    //EntityExistsException when 
    public void addAnnotation(String oid, String web, String t) throws EntityExistsException{
        tryIfICanSeeThisAnnotation(web, oid);
        Annotation a = new Annotation(MYID, t, web, oid);
        AMGR.add(a);
    }

    public void removeAnnotation(String tag, String web) {
        List<Annotation> la = AMGR.find(MYID, web, tag);
        if (!la.isEmpty()) {
            ListIterator<Annotation> it = la.listIterator();
            while (it.hasNext()) {
                AMGR.remove(it.next());
            }
        }
        List<Friends> lf = FMGR.find(MYID);
        ListIterator<Friends> it = lf.listIterator();
        while (it.hasNext()) {
            la = AMGR.find(it.next().getFriendID(), web, tag, MYID);
            ListIterator<Annotation> ita = la.listIterator();
            while (it.hasNext()) {
                AMGR.remove(ita.next());
            }
        }

    }
    
    public void addFreinds(String fid){
        FMGR.add(MYID, fid);
    }

    private void tryIfICanSeeThisAnnotation(String web, String id) {
        if (!id.equals(MYID)) {
            try {
                FMGR.tryIsFriend(MYID, id);
            } catch (NoResultException nre) {
                throw new NoResultException("I cannot see website of a stranger.");
            }
        }
    }
}
