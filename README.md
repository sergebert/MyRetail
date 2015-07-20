Serge's MyRetail programming case study
==============

Welcome to Serge's MyRetail app's README file
--------------
*Friday, July 17, 2015*

**This document includes the following:**

- Instructions for testing, running and interacting with the MyRetail app
- Musings concerning my design decisions throughout the development process


**Internal list of tasks associated with working on the MyRetail programming case study:**

- Carefully read though the programming case study
- Considered several tech stacks, including Java, C#, Groovy/Grails
- Looked into using Swagger (http://swagger.io/). I'd be inclined to consider such a framework, but don't feel I have enough time right now to incorporate it.
- Decided to go create the RESTful web service for this programming example using:
	
	- Java with JAX-RS 2.0
	
	- Tomcat
	
	- IntelliJIDEA
	
- This may result in more code that's verbose but for the purposes of this programming exercise I've decided that will have to do. I considered that Groovy/Grails may be better, however for me, there's too much "magic" that happens that I don't yet understand; implemention would probably have been faster using this stack, but if I ran into a bug of some kind, I may have ended up stuck and didn't want to face the prospect of being unable to complete this case study. 

Setting up the development environment
--------------
**Note:** this MBP was a relatively recent aquisition and to date I had only used it for iOS development. So prior to working on this app I had Xcode all set up and had been using that extensively, but that was about it in terms of software development environments. 

The Servlet 4.0 specification is out and Tomcat 9.0.x supports it. However, at this point it appears that Tomcat 8.0 is the best Tomcat version and it is supporting the 3.1 Servlet Spec. 

**Hardware:** Mac Book Pro, 15 inch, 2.2 GHz Intel Core i7 

**Operating System:** OS X Yosemite, Version 10.10.3

- Purchased the "MD Lite" app from the App Store to create/edit my README.md file

**Installing JDK 8 on OS X 10.10 Yosemite**

- Ran my first "JavaIsSetUpOnMyMachine" unit test which **failed** on my new MacBookPro.  Namely, I ran the Terminal app, I ran "java -version" which, of course, failed. 

*Java is not (pre-)installed anymore on OS X 10.7, so I need to address that first.  As of today, Java SE Development Kit 8u51 (i.e., JDK 8, update 51) is the latest version.*

- Downloaded the latest JDK from here: *http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html*

	The JDK installer package comes in an dmg and installed easily on my MBP.  

- Reopened the Terminal app again and re-ran my previously failed "JavaIsSetUpOnMyMachine" unit test by re-running the "java -version" command.  This time, the unit test passed, because now, I see something like this:

	dilberts-MacBook-Pro:~ dilbert$ java -version

	java version "1.8.0_51"

	Java(TM) SE Runtime Environment (build 1.8.0_51-b16)

	Java HotSpot(TM) 64-Bit Server VM (build 25.51-b03, mixed mode)

- Set my JAVA_HOME by running the following command in Terminal:

	*echo export "JAVA_HOME=\$(/usr/libexec/java_home)" >> ~/.bash_profile*

**Installing Tomcat 8 on OS X 10.10 Yosemite**

- Ran my "TomcatIsSetUpOnMyMachine" unit test which **failed**.  More specifically, I opened a FireFox browser and typed in the following **"http://localhost:8080/"** which resulted in, among other things, a page that included the following message:
*"Firefox can't establish a connection to the server at localhost:8080."*

Went to http://tomcat.apache.org ⇒ Download ⇒ Tomcat 8.0 ⇒ “8.0.24” ⇒ Core ⇒ "tar.gz" package from here: *http://tomcat.apache.org/download-80.cgi*

- Downloaded the binary distribution

- Moved the binary folder from my “~/Downloads” folder to the working folder I set up on my MBP for this exercise

- Double-clicked the downloaded tarball (e.g., "apache-tomcat-8.0.24.tar.gz" to expand it into a folder (e.g., "apache-tomcat-8.0.24”).

- Moved the extracted folder (e.g., "apache-tomcat-8.0.24”) to "/Applications".

- Renamed the folder to "tomcat"

- Verified Configuration of my Tomcat Server.  Tomcat configuration files are located in the "conf" sub-directory of my recently setup and renamed directory - ”/Applications/tomcat”

There are 4 configuration XML files (please see below), which I reviewed and validated.  For example, under ”conf\server.xml”, I validated that the TCP Port Number was set to “8080” though this value is configurable.

    server.xml
    web.xml
    context.xml
    tomcat-users.xml

- Started up my Tomcat server.  Tomcat was installed in "/Applications/tomcat", so to start my Tomcat server, I opened a new "Terminal" and typed in the following commands:

	// Change current directory to Tomcat's binary directory

	$ cd /Applications/tomcat/bin
 
	// Start tomcat server

	$ ./catalina.sh run

This time, I saw something like this in the Terminal window:

	dilberts-MacBook-Pro:bin dilbert$ ./catalina.sh run
	Using CATALINA_BASE:   /Applications/tomcat
	Using CATALINA_HOME:   /Applications/tomcat
	(skipping a lot of lines here)
	ROOT has finished in 11 ms
	17-Jul-2015 13:53:41.254 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
	17-Jul-2015 13:53:41.258 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["ajp-nio-8009"]
	17-Jul-2015 13:53:41.258 INFO [main] org.apache.catalina.startup.Catalina.start 	Server startup in 471 ms

- Re-ran my "TomcatIsSetUpOnMyMachine" unit test and re-typed in the following **"http://localhost:8080/"** 
- This time, I saw the "Apache Tomcat/8.0.24" home page and was able to validate that Tomcat was up and running.  

**Installing IntelliJ IDEA on OS X 10.10 Yosemite**

- Downloaded IntelliJ IDEA Ultimate Edition (Version: 14.1.4) from *https://www.jetbrains.com/idea/download/*

- Moved the downloaded file to to the working folder I set up on my MBP for this exercise

- Double clicked on the .dmg file, then, dragged the IDEA app over to my  "~/Applications" folder

- Double clicked on the IDEA icon within the "~/Applications" folder and walked through the initial setup screens for IDEA.

- Purchased a one-year IntelliJ IDEA 14.x Personal License

- Installed IntelliJ IDEA 14.x Personal License key after receiving it the appropriate link via email

**Validating the development environment by creating IntelliJ IDEA projects for both client and server-side Web Service parts**

**1. Creating the Web Service**

A personal note: setting up my Web Service app project within IntelliJ IDEA and getting it to deploy properly to Tomcat turned out to be an exceptionally difficult task.  I had relatively little problem getting my initial code to build, but getting it to deploy and work on Tomcat, despite a substantial amount of online research to resolve various problems, required an enormmous amount of time.  

- Created a maven-based project in Intellij
- Created an initial package and directory for my Java class(es) 
- In the dependencies section of my pom file, I add dependency to jersey 
- In the web.xml file, I added servlet configuration 
- Added my first Java-based class/file.  My initial Java class was the equivalent of a "Hello World" type of RESTful Web Service app
- Updated the Tomcat ""tomcat-users.xml"" file
- Updated the Tomcat "settings.xml" file
- Created a new "Local Tomcat" configuration in Intellij IDEA to host my web service
- Built and deployed to Tomcat
- Reviewed Tomcat logs; after many initial failures at last everything deployed and appeared to be running without error.

**2. Testing the Web Service**

- Entered the following URL in my FireFox (Version 39) browser:
http://localhost:8080/rest/hello
- Saw a corresponding response from my "Hello World" web service, indicating success.


Next Steps in the Development Process
--------------
*Important note:** One of the most important principles (as was written by Jim McCarthy, of Microsoft Corporation - see "21 Rules of Thumb for Shipping Great Software on Time") I adhere to in software development is:

*Get to a known state and stay there*

So, having finally established a running web service, my approach was to modify my application in small steps, rigorously testing each step along the way.  That way, if I made a mistake, I'd have a reasonably good idea of where to start looking for it. That way, making many changes relatively rapidly is doable, so long as the viability of the system is constantly checked throughout the development process.  In other words, my natural way of developing software is through incremental and iterative change, while testing those changes throughout the development process.

Practially, this meant that once I got my initial "Hello World" web service up and running, I then immediately began to incrementally change this initial "Hello World" application into something more substantive and able to fulfill the requirements of the "MyRetail" programming case study.  My approach was as follows:

1. Choose the highest priority item from the list of the MyRetail requirements
2. Whenever reasonably possible, write a test first, most often a unit test, though sometimes, an integration test may be more appropriate.
3. Run the test - it should initially fail.  
4. Run all previous tests - they should still succeed.
5. Implement the change
6. Re-run the newest test(s) - at some point, it/they should succeed.  If need be, based on new insight, add new tests or even modify the original test(s).
7. Run all previous tests - they should still succeed.
8. Whenever possible, test the change from a consumer's point of view (consumer could be a person or another piece of software)
9. Lather, rinse, repeat

**Overview of turning "Hello World" into Serge's "MyRetail"**

Note: thoroughly tested each of these changes

- Changed project/servelet name from HelloWorld to MyRetail
- Changed package and Java class and used "com.myretail.*" as base package name.  
- Created the "com.myretail.services" package
- Renamed primary Java class to "MyRetailService"
- Changed the path and the signature of the HelloWorld web service.  New base URL (I used this as a "heartbeat" type of method, just to make sure the web services were up and running on Tomcat, every time I rebuilt and re-deployed) is:

**http://localhost:8080/MyRetail/products**
 
- Added a new "getProduct" web service within the MyRetailService Java class to conform to the requested web service URI as outlined in the programming case study.  Also, created a hard-coded JSON response from this method, just to test the invocation and JSON response of this new method.  New URL for this is:

**http://localhost:8080/MyRetail/products/{id}**

where "{id}" is replaced by a number.  Initially, I hard coded this to accept any integer value and the hard coded JSON response was:

{"name":"The Big Lebowski","id":15117729,"current_price":"13.49","currency_code":"USD"}

- Created the "com.myretail.domain" package
- Created a new Product class under the "domain" package
- Hard coded the creation of a new Product object in the MyRetailService class under the "getProduct" web service.  Then, created a modified JSON response for the web service based on values from the Product object.  So the response is still the same and is still hard coded, but the hard coded values are now kept within the Product object.

**Design note:** The Web Service itself should be a simple and highly cohesive class.  Its primary job is to field incoming requests for a Product and send back a reply.  The getProduct Web Service should either return a JSON representation of a product, or, if it fails, should return an error.  That's it.  The Web Service shouldn't have to know anything about where the Product came from.  With this in mind, I decided to create a "model" that the getProduct Web Service should use to ask for Products.  If one exists, it will come back from the model, otherwise, the model will return null.  In this case, the model is what's going to do some aggregation work, not the Web Service.  Note, in this case, I decided that the model should be a thread safe singleton.  The job of the model is to provide Products to the Web Service.  

- Created the "com.myretail.model" package
- Created a new ProductModel class under the "model" package 

**Design note:** I deciced to separate the return of Product object, which the model will take care of, from the encoding of the Product object to a JSON format, so I added a new package and class to take care of the JSON encoding

- Created the "com.myretail.encoder" package
- Created a new JSONProductEncoder class under the "encoder" package 

- Wired up the Web Service to use the Product model to retrieve a product and also, to use the JSON encoder to encode the retrieved Product object to JSON

- Based on the "HTTP request" paragraph of the Programming Case Study document, after messing with the URL provided, along with the Target.com website, along with a JSON parser, I was able to figure out how the PRODUCT ID value was being used in the URL provided within the exercise and how that id corresponds to products on the Target.com web site.  Based on this information, I created a small table of products (about 7 of them) I looked up on Target.com, mostly getting their respective product ids.  I then double checked that that "HTTP request" to the api.target.com site would work for each product, by modifying the orignal URL supplied in the Programming Case Study document.  Based on all of that, I created a hash map within the Product Model class to store a number of Product objects, based on the table of products I had put together in a separate document.  I then modified the Product Model class to return a product if it is supplied the proper product ID (one of 7) or null.  I then modified the getProduct Web Service to either return the JSON of a product, provided the product exists, or to throw a "NOT FOUND" exception. 

- Added an HTTP call out to api.target.com to pull down JSON-based data for a Product in response to an incoming call to the MyRetail web service.  Am crudely parsing the JSON and based on the best value I could find for "product name" (from the Programming Case Study document), I chose the "general_description" value in the JSON and based on that, am setting the "name" value on the Product object.  

**Misc Notes:** 
As I worked, I added dependency entries in my POM file for, among other things:

- JUnit
- Log4J
- HttpClient
- JSON

**Notes on JUnit tests**

I didn't have enough time to include complete test coverage for all of my work.  In general, I would do so.  However, for the purposes of this POC exercise, I did set up and configure JUnit within my IntelliJ IDEA project and added a test for my ProductModel class.   


Instructions
--------------

**Building and Running**

I have taken the time to set up the MyRetail programming case study as an IntelliJ 14.0 project for a web service using Jersey 2 and Tomcat 8, complete with Maven support. 

I have put a copy of this working project on GitHub here:

[Todo: add link to GibHub repository here!]

In order to build and run this project, you should only need to clone it, open it up in IntelliJ 14, and change the path of your Tomcat 8 installation.

Setting up Tomcat - assuming you've previously installed Tomcat (as outlined above for example), the next step is making IntelliJ aware of Tomcat so that it can deploy the MyRetail application seamlessly.  In IntelliJ, in the Tomcat Server dialog, navigate to the installation directory of Tomcat.  After this is done, the correct version should be reported by the dialog. 

Creating a Run & Debug Configuration - before Tomcat can serve the MyRetail application, you’ll need to set it up. Do this by creating a new run configuration. This is done by navigating through Run > Edit Configurations… > “+” > Tomcat Server > Local. You may name this whatever you like.  During this step, you'll need to select the artifact for Tomcat to deploy. Choose the Deployment tab, clicking “+”, and choosing Artifact. Select the only artifact listed then choose Apply and finally OK.

After building and deploying this application, you may run a "smoke test" of sorts by entering the following URL into your local browser:

**http://localhost:8080/MyRetail/products**

You should see a "Hi from MyRetail!" message

Secondly, you should be able to look up one of seven (7) products by entering the following URL into your local browser:

**http://localhost:8080/MyRetail/products/14423830**

You should see a JSON-type of response like this (it may not look exactly like the following, but it should be close):

{"externalLookUpURL":"https://api.target.com/products/v3/14423830?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz","id":14423830,"current_price":0,"currency_code":"USD"}


