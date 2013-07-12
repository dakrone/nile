(defproject nile "1.1-SNAPSHOT"
  :description "Stream utilities for everyday Clojure use."
  :url "https://github.com/dakrone/nile"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[commons-io "2.4"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.5.1"]]}}
  :min-lein-version "2.0.0"
  :plugins [[lein-bikeshed "0.1.2"]])
