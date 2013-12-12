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

(deftest test-incrementing-date
  (let [date-string "2009-10-31"
        n-day (increment-day date-string)
        n-month (increment-month date-string)
        n-year (increment-year date-string)]
    (is (= n-day (date "2009-11-01")))
    (is (= n-month (date "2009-11-30")))
    (is (= n-year (date "2010-10-31")))))

(deftest test-decrementing-date
  (let [date-string "2009-10-31"
        n-day (decrement-day date-string)
        n-month (decrement-month date-string)
        n-year (decrement-year date-string)]
    (is (= n-day (date "2009-10-30")))
    (is (= n-month (date "2009-09-30")))
    (is (= n-year (date "2008-10-31")))))
