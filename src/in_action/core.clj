(ns in-action.core
  (:use compojure.core, ring.adapter.jetty)
  (:require [in-action.chap10_03 :refer :all]))

(defn -main [& args]
  (run-jetty (var hello) {:port 8080 :join? false}))

; To run the Ring examples
;(ns in-action.core
  ;(:use ring.middleware.params)
  ;(:use ring.adapter.jetty)
  ;(:require [in-action.chap10_02 :refer :all]))

;(defn -main [& args]
  ;(run-jetty echo-app {:port 8080})
;)
