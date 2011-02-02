
# Example Compojure application #

This example application is only intended for developers that use the leiningen-war plugin. Developers starting out doing web development in clojure are recommended to use the [lein-ring](https://github.com/weavejester/lein-ring) plugin.

This project contains a base web application deployable as a WAR file on a standard java webserver (which is sometimes known as a "servlet container"). It is used to demonstrate creating a deployable application with a 'projectname' of your choice:

     git clone -o example https://github.com/alienscience/compojure-war-example.git <projectname>
     cd <projectname>
     lein deps

The file `src/routes.clj` contains the compojure routing information. An example dynamic web page is shown in the file `src/pages.clj`. Absolute links can be created using the `link` function in `src/context.clj`. An example static html file is found in `src/index.html`.
 
## Features ##

### Has a development mode which runs a standalone jetty server. ###

This is a standard setup when running Compojure. During development the standalone server handles static html files with the same paths as the deployed application. It also includes a html stacktrace when exceptions are raised.

In a repl type:
     (require 'boot)
     (boot/start)
to start the webserver on port 8081. If you change the routes of the webserver (in the file `src/routes.clj`) a restart is needed to pick the changes:
     (boot/restart)

### Has a deployment mode which is runnable in a non-root web context. ###

If you deploy a WAR file on a java webserver you may be caught out to find that the path to your application is `/name-of-war-file/path-in-web-xml/` which, in the case of this example, is `/name-of-war-file/app/`. 

This application includes a wrapper that removes the 'context path' so that both the development and deployed application will appear internally to have dynamic content on `/app/` and static content on `/`. A function called `context/link` is available which builds absolute links that are correct in both development and deployed mode:
 
    (ns example
       (:require context))
       
    (str "An absolute path for use in a link: "
         (context/link "/some/path"))

### Uses the java webserver to serve static files ###

During development static file are served from 'src/html' with the path `/`. For instance `/` will be served from 'src/html/index.html'.

After deployment, the static files will be served by the java webserver with the path included. For instance `/name-of-war-file/` will serve the index.html file described above.

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
Deploy to the webserver (e.g standalone Jetty):
    cp example.war ~/jetty/webapps/
Start the webserver (e.g standalone Jetty):
    cd ~/jetty/webapps
    java -jar start.jar
The web application, in the example deployment above, will be reachable at:
    http://localhost:8080/example/



