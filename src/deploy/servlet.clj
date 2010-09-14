
(ns deploy.servlet
  (:use ring.util.servlet)
  (:require routes)
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defservice routes/app-routes)


