
(defproject compojure-war-example "0.0.3"
  :description "Deployable hello world in compojure"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.5.3"]
                 [ring/ring-servlet "0.3.4"]
                 [hiccup "0.3.1"]]
  ;; The jetty adaptor is only used during development
  ;; Leiningen-war should be a dev dependency
  :dev-dependencies [[ring/ring-jetty-adapter "0.3.4"]
                     [ring/ring-devel "0.3.4"]
                     [swank-clojure "1.2.1"]
                     [uk.org.alienscience/leiningen-war "0.0.10"]]
  ;; A servlet class must compiled for use in a java web server
  :aot [deploy.servlet])

