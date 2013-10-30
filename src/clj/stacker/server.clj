(ns stacker.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as ring-resource]
            [ring.middleware.content-type :as content-type]
            [ring.middleware.file-info :as file-info]
            [ring.util.response :as response]
            [liberator.core :refer [resource defresource]]
            [compojure.core :refer [defroutes ANY]]
            [stacker.templates :as templates]
            [clojure.data.json :as json])
  (:gen-class))

(defresource resource-app
  :available-media-types ["text/html"]
  :handle-ok (fn [_] (:app templates/pages)))

(defresource resource-yapping
  :available-media-types ["application/json"]
  :handle-ok (fn [_] (println :resource-yapping)
               (json/write-str
                {:abbawabba 1 :babbawabba 2 :now (.getTime (java.util.Date.))})))

(defroutes app-routes
  (ANY "/" [] (fn [_] (response/redirect "/help.html")))
  (ANY "/app" [] resource-app)
  (ANY "/yapping" [] resource-yapping))

(def app
  (-> app-routes
      (ring-resource/wrap-resource "public")
      (file-info/wrap-file-info)))

(defn -main [& args]
  (jetty/run-jetty #'app {:port 4001}))

