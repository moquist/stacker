(ns stacker.client
  (:require [clojure.browser.repl :as repl]
            [clojure.browser.net :as cb-net]
            [clojure.browser.event :as cb-event]
            [dommy.core :as dommy])
  (:use-macros
   [dommy.macros :only [by-id sel1]]))

(repl/connect "http://localhost:9000/repl")

(defn callback-err [ev]
  (.log js/console "Error: " ev))

(defn callback-success [ev]
  (let [response-text (.getResponseText (.-target ev))]
    (.log js/console response-text)
    (dommy/append! (sel1 :#ticker) [:p response-text])))

(def yapping-uri "http://localhost:4001/yapping")

(def xhr (cb-net/xhr-connection.)) ; local state!
(cb-event/listen xhr :error callback-err)
(cb-event/listen xhr :success callback-success)

(defn fetch-updates []
  (cb-net/transmit xhr yapping-uri))

(defn handle-click []
  (js/alert "Hello!")
  (fetch-updates))

(defn poll
  "Request new data every 10 seconds." 
  []
  (let [timer (goog.Timer. 10000)]
    (do (fetch-updates)
        (. timer (start))
        (cb-event/listen timer goog.Timer/TICK fetch-updates))))

(defn start-app
  "Start polling and listen for UI events."
  []
  (do (poll)
      (cb-event/listen (by-id :clickable) "click" handle-click)))

(start-app)


