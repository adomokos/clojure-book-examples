(ns cookbook.04-local-io-test
  (:require [clojure.test :refer :all]
            [cookbook.04-local-io :refer :all]))

(deftest accessing-resource-file-test
  (testing "reading the content out"
    (is (= (list "Lisp" "Python" "Clojure")
           (find-people "people.edn")))))
