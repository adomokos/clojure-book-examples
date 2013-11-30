(ns in-action.core
  (:require [in-action.chap03_04 :refer :all]
            ))

(defn -main [& args]
  (println (meta (var run-describe-salary-3-with-no-bonus)))
)
