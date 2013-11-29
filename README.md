JavaLevelThree-Project-Reviews
==============================

The project on the Java Level Three JavaEE course I attended during November 2013.

There are CSV files in the Reviews-jpa project that can populate the database.
Populate the Products and ReviewUsers tables first, then the Reviews table last.

There are files in the Reviews-web which contain the REST APIs exposed by the web application.
One of the files has the JSON for the POST.

Project Setup
-------------

To deploy the project to jBoss, follow the steps in order:

 * Start the Derby database.
 * Start jBoss.
 * Deploy the Reviews application.
 * Populate the database via the REST POST calls, or via the CSV files mentioned above.
 * Use a REST service, such as POSTman for Chrome, to operate the REST API.


Issues
------

The current issues with the project are:

 * It keeps adding a user and product for every Review entry.
 * Checks for existing users and their correct passwords are only stubs that return true.
 * There isn't an REST API to create a user.