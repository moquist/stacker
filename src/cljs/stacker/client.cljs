(ns stacker.client
  (:require [clojure.browser.repl :as repl]
            ;[goog.net.XhrIo :as xhrio]
            [clojure.browser.net :as gnet]
            [clojure.browser.event :as gevent]
            [dommy.core :as dommy])
  (:use-macros
   [dommy.macros :only [sel1]]))

(repl/connect "http://localhost:9000/repl")

(defn callback-err [ev]
  (.log js/console "Error: " ev))

(defn callback-success [ev]
  (let [response-text (.getResponseText (.-target ev))]
    (.log js/console response-text)
    (dommy/append! (sel1 :#ticker) [:p response-text])))

(def yapping-uri "http://localhost:4001/yapping")

(defn call-api []
  (let [xhr (gnet/xhr-connection.)]
    (gevent/listen xhr :error callback-err)
    (gevent/listen xhr :success callback-success)
    (gnet/transmit xhr yapping-uri)))

(call-api)

(defn handle-click []
  (js/alert "Hello!"))

(def clickable (.getElementById js/document "clickable"))
(.addEventListener clickable "click" handle-click)

