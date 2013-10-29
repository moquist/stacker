(ns stacker.templates
  (:require [hiccup.page]))

(def pages
  {:app (hiccup.page/html5
         [:head
          [:link {:rel "stylesheet" :href "css/page.css"}]]
         [:body
          [:div [:p#clickable "Click me!"]]
          [:div [:a {:href "/yap"} "Try this page."]]
          [:div#ticker [:p "Loading..."]]
          [:script {:src "js/cljs.js"}]])
   :yap (hiccup.page/html5
         [:head
          [:link {:rel "stylesheet" :href "css/page.css"}]]
         [:body
          [:div [:p "Here is a paragraph."]]
          [:div [:a {:href "/app"} "Get me over to app!"]]])})
