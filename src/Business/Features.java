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
 * A business layer class Implementing all functions required by the coursework
 * specification.
 *
 * @author Shiyao Zhang
 */
public class Features extends Business {

    /**
     * Constructs a <code>Business</code> object initializing Java Persistence
     * Query Language Manager of Person, Friends, and Annotation.
     *
     * @param pu The persistence unit used to connect database.
     */
    public Features(String pu) {
        super(pu);
    }

    /**
     * Show detailed information (e.g., ID, name, course, and email) about
     * myself.
     *
     * @return <code>null</code> if my information is not stored in the
     * database.
     */
    public Person myDetailInformation() {
        return PMGR.find(MY_ID);
    }

    /**
     * Show detailed information about all my friends.
     *
     * @return <code>null</code> if I don't have any friend.
     */
    public List<Person> allMyFriendsDetailInformation() {
        return allFriendsDetailInformation(MY_ID);
    }

    /**
     * Show detailed information about all friends of a specific person
     * represented by id. If a person found in the database is not persisted, an
     * empty {@link Person Person} object would be generated and set id as the
     * id found by {@link #FMGR FMGR}.
     *
     * @param id Id of the person.
     * @return <code>null</code> if there is no friends record stored in the
     * database.
     */
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

    /**
     * Find all web pages published by myself.
     *
     * @return <code>null</code> if I didn't publish any web page.
     */
    public Set<IdWebpage> allMyWebpages() {
        return allWebpages(MY_ID);
    }

    /**
     * Find all web pages published by a specific person.
     *
     * @param id Id of the person.
     * @return <code>null</code> if the specific person didn't publish any web
     * page.
     */
    public Set<IdWebpage> allWebpages(String id) {
        return new HashSet(AMGR.findWebpageWithId(id));
    }

    /**
     * Find all tags published by myself.
     *
     * @return <code>null</code> if I didn't publish any tag.
     */
    public List<String> allMyTags() {
        return allTags(MY_ID);
    }

    /**
     * Find all tags published by a specific person.
     *
     * @param id Id of the person.
     * @return <code>null</code> if the specific person didn't publish any tag.
     */
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

    /**
     * Find all web page published by my friends.
     *
     * @return <code>null</code> if no web page published by my friends.
     */
    public Set<IdWebpage> allMyFriendsWebsite() {
        return allFriendsWebsite(MY_ID);
    }

    /**
     * Find all web page published by a specific person described by id.
     *
     * @param id Id number of the specific person.
     * @return <code>null</code> if no web page published by the specific
     * friends.
     */
    public Set<IdWebpage> allFriendsWebsite(String id) {
        Set<Friends> sf = FMGR.findFriends(id);
        Set<IdWebpage> sa = new HashSet();
        for (Friends f : sf) {
            Set<IdWebpage> lfa = allWebpages(f.getFriendID());
            sa.addAll(lfa);
        }

        return sa;
    }

    /**
     * Find all tags published by my friends or myself on a specific web page.
     *
     * @param web Address of the specific web page.
     * @param oid Owner id of the specific web page.
     * @return List of tags.
     * @throws NoResultException if the specific web page is not published by a
     * friend or myself.
     */
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

    /**
     * Find all Annotations for a web page published by a friend or myself.
     *
     * @param web Web page address of the specific annotation.
     * @param oid Owner id of the Annotation.
     * @return List of Annotations sorted according to datetime.
     * @throws NoResultException if the specific web page is not published by a
     * friend or myself.
     */
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
    /**
     * Add a specific Annotation to the database.
     *
     * @param oid Owner id of the Annotation.
     * @param web Web page address.
     * @param tag Tag.
     * @return The Annotation added to the database.
     * @throws EntityExistsException if there exists an Annotation with same tag
     * published by myself.
     * @throws EntityNotFoundException if the specific web page is not found in
     * the database.
     *
     */
    public Annotation addAnnotation(String oid, String web, String tag) throws EntityExistsException {
        tryFriend(oid);
        List<Annotation> la = AMGR.find(oid, web);
        if (la.isEmpty()) {
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
