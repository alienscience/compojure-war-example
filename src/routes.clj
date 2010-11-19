
(ns routes
  (:use compojure.core)
  (:require [compojure.route :as route])
  (:require pages)    ;; src/pages.clj
  (:require context)  ;; src/context.clj
  (:use ring.middleware.stacktrace))


(defroutes app-routes

  ;; All dynamic content lives under /app/
  (GET  "/app/dump" request
    (pages/dump-request request))

  ;; During development the internal web server will serve static files
  ;; After deployment the java web server will serve static files and the
  ;; route below will not be used
  (route/files "/" {:root "src/html"})

  ;; Handle 404 when no routes match
  (route/not-found "Page not found"))


(wrap! app-routes context/wrap-context)


