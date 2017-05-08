/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import model.Person;
import model.Friends;
import model.Annotation;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

/**
 *
 * @author Bill
 */
public class Features extends Business {

    public Features(String pu) {
        super(pu);
    }

    public Person myDetailInformation() {
        return PMGR.find(MY_ID);
    }

    public List<Person> allMyFriendsDetailInformation() {
        return allFriendsDetailInformation(MY_ID);
    }

    public List<Person> allFriendsDetailInformation(String id) {
        Set<Friends> sf = FMGR.find(id);
        List<Person> lp = new ArrayList<>();
        for (Friends f : sf) {
            String fid = f.getFriendID();
            Person p = PMGR.find(fid);
            if (p == null) {
                p = new Person();
                p.setCourse(Person.COURSES.NULL);
                p.setEmail("null");
                p.setName("null");
                p.setID(fid);
            }
            lp.add(p);
        }

        return lp;
    }

    public List<String> allMyWebpages() {
        return allWebpages(MY_ID);
    }

    public List<String> allWebpages(String id) {
        List<Annotation> la = AMGR.find(id);
        ArrayList<String> ls = new ArrayList<>();
        ListIterator<Annotation> it = la.listIterator();
        while (it.hasNext()) {
            String web = it.next().getWebPage();
            ls.add(web);
        }
        return ls;
    }

    public List<String> allMyTags() {
        List<Annotation> la = AMGR.findByTagger(MY_ID);

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
        Set<Friends> sf = FMGR.find(MY_ID);
        ArrayList<String> lw = new ArrayList<>();
        for (Friends f : sf) {
            String fid = f.getFriendID();
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
        List<Annotation> la = AMGR.find(oid, web);//exception?

        la.sort((Object o1, Object o2) -> {
            Annotation a1 = (Annotation) o1;
            Annotation a2 = (Annotation) o2;
            return a1.getDatetime().toString().compareTo(a2.getDatetime().toString());
        });
        return la;
    }

    //EntityExistsException when 
    public void addAnnotation(String oid, String web, String tag) throws EntityExistsException {
        tryIfICanSeeThisAnnotation(web, oid);
        Annotation a = new Annotation(MY_ID, tag, web, oid);
        AMGR.add(a);
    }

    public void removeAnnotation(String tag, String web) {  //write AMTR.remove(My_ID, web,tag);
        List<Annotation> la = AMGR.find(MY_ID, web, tag);
        if (!la.isEmpty()) {
            ListIterator<Annotation> it = la.listIterator();
            while (it.hasNext()) {
                AMGR.remove(it.next());
            }
        }
        Set<Friends> sf = FMGR.find(MY_ID);
        if (!sf.isEmpty()) {
            for (Friends f : sf) {
                la = AMGR.find(f.getFriendID(), web, tag, MY_ID);
                ListIterator<Annotation> it = la.listIterator();
                while (it.hasNext()) {
                    AMGR.remove(it.next());
                }
            }
        }

    }

    private void tryIfICanSeeThisAnnotation(String web, String id) {
        if (!id.equals(MY_ID)) {
            try {
                FMGR.tryIsFriend(MY_ID, id);
            } catch (NoResultException nre) {
                throw new NoResultException("I cannot see website of a stranger.");
            }
        }
    }
}
