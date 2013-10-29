(defproject stacker "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1896"]
                 [ring "1.1.8"]
                 [compojure "1.1.5"]
                 [liberator "0.9.0"]
                 [prismatic/dommy "0.1.1"]
                 [org.clojure/data.json "0.2.3"]
                 [hiccup "1.0.4"]]
  :plugins [[lein-cljsbuild "0.3.2"]
            [lein-ring "0.8.7"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :cljsbuild { 
    :builds {
      :main {
        :source-paths ["src/cljs"]
        :compiler {:output-to "resources/public/js/cljs.js"
                   :optimizations :simple
                   :pretty-print true}
        :jar true}}}
  :main stacker.server
  :ring {:handler stacker.server/app})

