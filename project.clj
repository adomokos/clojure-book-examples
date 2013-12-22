(defproject in-action "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.3"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql "9.1-901.jdbc4"]
                 [ring "1.2.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.4"]
                ]
  :main in-action.core)
