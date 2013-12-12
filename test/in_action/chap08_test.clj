(ns in-action.chap08-test
  (:require [clojure.test :refer :all]
            [in-action.chap08_01 :refer :all]))

(deftest test-simple-date-parsing
  (let [date "2009-1-22"]
    (is (= (day-from date) 22))
    (is (= (month-from date) 1))
    (is (= (year-from date) 2009))))

(deftest test-as-string
  (let [date "2009-1-22"]
    (is (= (as-string date) "2009-01-22"))))
