
# Example Compojure application #

This project contains a "hello world" application deployable as a WAR file on a standard java webserver (which is sometimes known as a "servlet container").

## Features ##

### Has a development mode which runs a standalone jetty server. ###

This is a standard setup when running Compojure. During development the standalone server handles static html files with the same paths as the deployed application. It also includes a html stacktrace when exceptions are raised.

For more information see [this thread](http://groups.google.com/group/compojure/browse_thread/thread/3e988b6b29b787fe).

### Has a deployment mode which is runnable in a non-root web context. ###

If you deploy a WAR file on a java webserver you may be caught out to find that the path to your application is `/name-of-war-file/servlet-path/`. 

This application includes a wrapper that removes the 'context path' so that both the development and deployed application will appear internally to have dynamic content on `/app/` and static content on `/`. A function called 'link' is available which builds absolute links that are correct in both development and deployed mode.

For more information see [this thread](http://groups.google.com/group/compojure/browse_thread/thread/28df9365fd355299).

### Uses the java webserver to serve static files ###

During development static file are served from 'src/html' with the path `/`. For instance `/` will be served from 'src/html/index.html'.

After deployment, the static files will be served by the java webserver with the path included. For instance `/name-of-war-file/` will serve the index.html file described above.

For more information see [this thread](http://groups.google.com/group/compojure/browse_thread/thread/f8e7af677ada8536).

## Developing ##

Run a swank server:
    lein swank
Or a repl
    lein repl    
   
Start the development server on port 8081:   
    user> (require 'boot)
    nil
    user> (boot/start)
    #<Server Server@77a5ff38>
Hack and change as you wish.

Create a WAR file:
    lein uberwar
Copy to the webserver (e.g standalone Jetty):
    cp compojure-war-example-0.0.3.war ~/jetty/webapps/
Start the webserver (e.g standalone Jetty):
    cd ~/jetty/webapps
    java -jar start.jar




