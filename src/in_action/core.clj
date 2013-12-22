(ns in-action.core
  (:use ring.middleware.params)
  (:use ring.adapter.jetty)
  (:require [in-action.chap10_02 :refer :all]))

(defn -main [& args]
  (run-jetty echo-app {:port 8080})
)
