(ns cookbook.04-local-io
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn find-people [file-name]
  (->> file-name
       io/resource
       slurp
       edn/read-string
       (map :language)))
