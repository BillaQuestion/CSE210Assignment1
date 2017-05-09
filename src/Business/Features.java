/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import model.Person;
import model.Friends;
import model.Annotation;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import JPQLMgr.IdWebpage;

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
        Set<Friends> sf = FMGR.findFriends(id);
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

    public Set<IdWebpage> allMyWebpages() {
        return allWebpages(MY_ID);
    }

    public Set<IdWebpage> allWebpages(String id) {
        return new HashSet(AMGR.findWebpageWithId(id));
    }

    public List<String> allMyTags() {
        return allTags(MY_ID);
    }

    public List<String> allTags(String id) {
        List<Annotation> la = AMGR.findByTagger(id);

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

    public Set<IdWebpage> allMyFriendsWebsite() {
        return allFriendsWebsite(MY_ID);
    }

    public Set<IdWebpage> allFriendsWebsite(String id) {
        Set<Friends> sf = FMGR.findFriends(id);
        Set<IdWebpage> sa = new HashSet();
        for (Friends f : sf) {
            Set<IdWebpage> lfa = allWebpages(f.getFriendID());
            sa.addAll(lfa);
        }

        return sa;
    }

    public List<String> allTagsForAWebsitePublishedByFriendOrMyself(String web, String oid) throws NoResultException {
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

    public List<Annotation> allDatetimeSortedAnnotationForAWebsitePublishedByFriendOrMyself(String web, String oid) throws NoResultException {
        tryFriend(oid);
        List<Annotation> la = AMGR.find(oid, web);//exception?

        la.sort((Object o1, Object o2) -> {
            Annotation a1 = (Annotation) o1;
            Annotation a2 = (Annotation) o2;
            return a1.getDatetime().toString().compareTo(a2.getDatetime().toString());
        });
        return la;
    }

    //EntityExistsException when 
    public Annotation addAnnotation(String oid, String web, String tag) throws EntityExistsException {
        tryFriend(oid);
        List<Annotation> la = AMGR.find(oid, web);
        if(la.isEmpty()){
            throw new EntityNotFoundException();
        }
        Annotation a = new Annotation(MY_ID, tag, web, oid);
        AMGR.add(a);
        return a;
    }

    public void removeAnnotation(String tag, String web) {  //write AMTR.remove(My_ID, web,tag);
        List<Annotation> la = AMGR.find(MY_ID, web, tag);
        if (!la.isEmpty()) {
            ListIterator<Annotation> it = la.listIterator();
            while (it.hasNext()) {
                AMGR.remove(it.next());
            }
        }
        Set<Friends> sf = FMGR.findFriends(MY_ID);
        if (!sf.isEmpty()) {
            for (Friends f : sf) {
                AMGR.remove(f.getFriendID(), web, tag, MY_ID);
            }
        }

    }

    private void tryFriend(String id) throws NoResultException {
        if (!id.equals(MY_ID)) {
            FMGR.tryFriend(MY_ID, id);
        }
    }
}
