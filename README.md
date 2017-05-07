# CSE210Assignment1
A MySQL centered webpage annotation program
# Overview
The objective of this coursework is to develop a practical application based on the object oriented principles you learnt in the lectures. This application allows users to share Web pages among friends and to collaboratively annotate those pages.
# Description
## Database
The database contains 3 tables, which will be used by all of the users. The database has already been designed for you, and can be accessed by (usename: cse210, password: cse210, phpMyAdmin URL: http://srv-csse.xjtlu.edu.cn, or   IP:10.7.1.36). When accessing it from your Java code, you should use:  
conn = DriverManager.getConnection("jdbc:mysql://10.7.1.36:3306/cse210", "cse210", "cse210")  

The tables and the attributes are explained as follows.
1. TABLE PERSON(ID, name, course, email)
This table maintains the information about users. For the sake of simplicity, you should use your own university student ID (the primary key), name (e.g., Donald Trump), course (e.g, ICS, CST or DMT), email (e.g., d.trump@xjtlu.edu.cn). You need to insert your own record to the database.
2. TABLE FRIENDS(myID, friendID)
This table maintains the relationships between one user and his/her friends. myID is your own ID and friendID is the ID of your friend. Both are foreign keys referencing the ID of the table PERSON. Note that friend relationship should be transitive. This means if you add someone as a friend, you should also declare that you are a friend of him/her. Duplicates are not allowed. For testing purposes, you need to insert at least three friends for yourself. 
3. TABLE ANNOTATION(taggerID, tag, webPage, ownerID, datetime)
This table stores the information about the annotations. An annotation represents the information about a tagging process, e.g., a tagger uses a specific tag to describe the content of a webpage. tagger ID is the ID of a tagger (who provides a tag); tag is any word or word phrase to describe a Web page; webPage is represented using a URL; ownerID is the ID of the owner of the Web page; datetime records the timestamp of the tagging. For testing purposes, you need to insert at least ten annotations for yourself.
## Assumption
To simplify the application design, it is assumed that:
* When a Web page is shared, an annotation record must be added to the table ANNOTATION (taggerID is the same as OwnerID). 
* A user can add more than one tag to a Web page no mater created by him/her or his/her friends.
* A user can view and add annotations published by himself/herself and his/her friends; but he/she can only remove annotations published by himself/herself or he/she is the owner of the Web page. Other peopleâ€™s data should NOT be visible to him/her.
* One user can only share/publish a Web page once; while different users can share/publish the same Web page.
* Different users can use the same tag to annotate the same Web page.
* Duplicate annotations are not allowed.
## Objects needed for the Application
You need to design a number of information objects to represent the objects and the needed methods for this application. You need to design a class(es) for the testing purposes and some other helper classes in order to realise the functionalities (as specified below). You are welcome to have your own design following good programming practice and style.
* Person â€?represents the relevant information about a user;
* Tag â€?represents a tag;
* Annotation â€?represents the information about a tagging process, as explained above.
# Tasks
You should complete the following tasks. 
1. Develop information objects and helper classes (with their methods) needed for this application with good coding style (e.g., object orientation, source code, functional decomposition), and design an easy-to-use interface for testing (using the command window is enough).
2. Consider efficiency and robustness in your coding (e.g., exception handling and data structures)
3. Complete the following tasks which will be checked and marked during the demonstration sessions. You should design a method for each of the tasks (that method may call other methods).
Note: FAILING TO PRESENT AT THE DEMONSTRATION SESSION WILL RESULT IN ZERO MARK FOR TESTING.
    * Show detailed information (e.g., ID, name, course, and email) about yourself.
    * Show detailed information about all your friends.
    * Show all the Web pages that you have published. 
    * Show all the personal tags (duplicates should be removed), i.e., all the tags that have been added by you in the database.
    * Show Web pages published by all of your friends.
    * Show all tags that have been used to annotate a Web page whose owner is you or a friend. Duplicates should be removed. (Hint: this method should accept the following parameters: a Web page in terms of URL string and an ID). 
    * Show all annotations for a Web page whose owner is you or a friend, sorted chronologically according to the datetime.
    * Add an annotation to a Web page whose owner is you or a friend. Note: duplicates, i.e., same annotations (excluding datetime) are NOT allowed.
    * Remove an annotation(s) of a Web page. This method should receive the two parameters: Web page (URL) and a tag. Note that if you are the owner of this page, then all the annotations with the tag should be removed. If you are the tagger, only the annotation created by you is removed. 
4. Use Javadoc comments to document your codes and generate HTML javadoc.
