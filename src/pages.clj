
(ns pages
  "Dynamically generated pages"
  (:use clojure.contrib.pprint)
  (:require context)
  (:use hiccup.core))

(defn dump-request
  "Dumps the given Ring request"
  [r]
  (html
   [:html
    [:head
     [:title "Request Dump"]
     [:link {:rel "stylesheet"
             :href (context/link "/stylesheet.css")
             :type "text/css"}]]
    [:body
     [:h1 "The Ring request"]
     [:p "This is a dynamic page containing a dump of the Ring request"]
     [:pre
      (with-out-str (pprint r))]]]))
