# CSE210Assignment1
A MySQL centered webpage annotation program
# Overview
The objective of this coursework is to develop a practical application based on the object oriented principles you learnt in the lectures. This application allows users to share Web pages among friends and to collaboratively annotate those pages.
# Description
## Database
The database contains 3 tables, which will be used by all of the users. The database has already been designed for you, and can be accessed by (usename: cse210, password: cse210, phpMyAdmin URL: http://srv-csse.xjtlu.edu.cn, or   IP:10.7.1.36). When accessing it from your Java code, you should use: conn = DriverManager.getConnection("jdbc:mysql://10.7.1.36:3306/cse210", "cse210", "cse210")
The tables and the attributes are explained as follows.
* TABLE PERSON(ID, name, course, email)
This table maintains the information about users. For the sake of simplicity, you should use your own university student ID (the primary key), name (e.g., Donald Trump), course (e.g, ICS, CST or DMT), email (e.g., d.trump@xjtlu.edu.cn). You need to insert your own record to the database.
* TABLE FRIENDS(myID, friendID)
This table maintains the relationships between one user and his/her friends. myID is your own ID and friendID is the ID of your friend. Both are foreign keys referencing the ID of the table PERSON. Note that friend relationship should be transitive. This means if you add someone as a friend, you should also declare that you are a friend of him/her. Duplicates are not allowed. For testing purposes, you need to insert at least three friends for yourself. 
* TABLE ANNOTATION(taggerID, tag, webPage, ownerID, datetime)
This table stores the information about the annotations. An annotation represents the information about a tagging process, e.g., a tagger uses a specific tag to describe the content of a webpage. tagger ID is the ID of a tagger (who provides a tag); tag is any word or word phrase to describe a Web page; webPage is represented using a URL; ownerID is the ID of the owner of the Web page; datetime records the timestamp of the tagging. For testing purposes, you need to insert at least ten annotations for yourself.
