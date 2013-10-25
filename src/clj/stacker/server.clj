(ns stacker.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resources]
            [ring.util.response :as response]
            [liberator.core :refer [resource defresource]]
            [compojure.core :refer [defroutes ANY]])
  (:gen-class))

(defn render-app
  "This is now a liberator resource handler, so just return the value
   that would be :body in the usual ring handler response map
   (see https://github.com/ring-clojure/ring/wiki/Concepts#handlers).

   Currently ignoring the ctx argument passed by liberator: 'Every
   decision function takes a single parameter, the context, which is a
   map.' (see
   http://clojure-liberator.github.io/liberator/tutorial/decision-graph.html)."
  [_]
  (str "<!DOCTYPE html>"
       "<html>"
       "<head>"
       "<link rel=\"stylesheet\" href=\"css/page.css\" />"
       "</head>"
       "<body>"
       "<div>"
       "<p id=\"clickable\">Click me!</p>"
       "</div>"
       "<script src=\"js/cljs.js\"></script>"
       "</body>"
       "</html>"))

(defresource resource-app
  :available-media-types ["text/html"]
  :handle-ok render-app)

(defroutes app-routes
  (ANY "/" [] (fn [_] (response/redirect "/help.html")))
  (ANY "/app" [] resource-app))

(def app
  (-> app-routes
      (resources/wrap-resource "public")))

(defn -main [& args]
  (jetty/run-jetty #'app {:port 3000}))

