# Files location
* Sources files are in folder src;
* Javadoc documentations are in folder dist\javadoc. Use index.html;
* Built classes are in folder build\classes.

# Dependency
This project is dependent on "derby.jar", "derbyclient.jar", "eclipselink.jar", "javax.persistence_2.1.0.v201304241213.jar", "mysql-connector-java-commercial-5.1.41-bin.jar", and "org.eclipse.persistence.jpa.jpql_2.5.2.v20140319-9ad6abd.jar" files. Add these files into environment or the project folder.

# Test
Main method is in pakcage run class WebpageAnnotation.
To test methods, following codes are required in main method:
~~~
Features f = new Features(PERSISTENCE_UNIT);
InitializeData i = new InitializeData(PERSISTENCE_UNIT);
UI ui = new UI(f, i);
~~~
Above code would generate an UI object in which all methods are implemented for functions required.
