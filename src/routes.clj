
(ns routes
  (:use compojure.core)
  (:require [compojure.route :as route])
  (:use ring.middleware.stacktrace))

(declare link)

(defroutes app-routes

  ;; All dynamic content lives under /app/
  (GET  "/app/dump" req
    (str req))
  (GET  "/app/linktest" []
    (str "Test <a href=\"" (link "/index.html") "\">link</a>."))

  ;; During development the internal web server will serve static files
  ;; After deployment the java web server will serve static files and the
  ;; route below will not be used
  (route/files "/" {:root "src/html"})

  ;; Handle 404 when no routes match
  (route/not-found "Page not found"))


;;======== Code below handles paths when running as a servlet ==================

;; This caches the context path of the servlet
(def context-path (atom nil))

(defn- get-context-path
  "Returns the context path when running as a servlet"
  ([] @context-path)
  ([servlet-req]
     (if (nil? @context-path)
       (reset! context-path (.getContextPath servlet-req)))
     @context-path))

(defn- wrap-context
  "Removes the deployed servlet context from a URI when running as a
   deployed web application"
  [handler]
  (fn [request]
    (if-let [servlet-req (:servlet-request request)]
      (let [context (get-context-path servlet-req)
            uri (:uri request)]
        (if (.startsWith uri context)
          (handler (assoc request :uri
                          (.substring uri (.length context))))
          (handler request)))
      (handler request))))

(wrap! app-routes :context)

(defn link
  "Builds a link, including context path, out of the given url"
  [url]
  (str (get-context-path) url))

