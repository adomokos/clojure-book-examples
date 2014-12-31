(ns in-action.chap10-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str :only :join])
  (:import (java.text SimpleDateFormat)
           (java.util Calendar GregorianCalendar)))

(defn date [date-string]
  (let [f (SimpleDateFormat. "yyyy-MM-dd")
        d (.parse f date-string)]
    (doto (GregorianCalendar.)
      (.setTime d))))

(defn day-from [d]
  (.get d Calendar/DAY_OF_MONTH))

(defn month-from [d]
  (inc (.get d Calendar/MONTH)))

(defn year-from [d]
  (.get d Calendar/YEAR))

(defn pad [n]
  (if (< n 10) (str "0" n) (str n)))

(defn as-string [date]
  (let [y (year-from date)
        m (pad (month-from date))
        d (pad (day-from date))]
    (str/join "-" [y m d])))


;(defn increment-day [d]
  ;(doto (.clone d) (.add Calendar/DAY_OF_MONTH 1)))

;(defn increment-month [d]
  ;(doto (.clone d) (.add Calendar/MONTH 1)))

;(defn increment-year [d]
  ;(doto (.clone d) (.add Calendar/YEAR 1)))

;(defn decrement-day [d]
  ;(doto (.clone d) (.add Calendar/DAY_OF_MONTH -1)))

;(defn decrement-month [d]
  ;(doto (.clone d) (.add Calendar/MONTH -1)))

;(defn decrement-year [d]
  ;(doto (.clone d) (.add Calendar/YEAR -1)))

(defn date-operator [operation field]
  (fn [d]
    (doto (.clone d)
      (.add field (operation 1)))))

(def increment-day (date-operator + Calendar/DAY_OF_MONTH))
(def increment-month (date-operator + Calendar/MONTH))
(def increment-year (date-operator + Calendar/YEAR))
(def decrement-year (date-operator - Calendar/DAY_OF_MONTH))
(def decrement-year (date-operator - Calendar/MONTH))
(def decrement-year (date-operator - Calendar/YEAR))

(deftest test-simple-date-parsing
  (let [d (date "2009-1-22")]
    (is (= (day-from d) 22))
    (is (= (month-from d) 1))
    (is (= (year-from d) 2009))))

(deftest test-as-string
  (let [d (date "2009-01-22")]
    (is (= (as-string d) "2009-01-22"))))

(deftest test-incrementing-date
  (let [d (date "2009-10-31")
        n-day (increment-day d)
        n-month (increment-month d)
        n-year (increment-year d)]
    (is (= (as-string n-day) "2009-11-01"))
    (is (= (as-string n-month) "2009-11-30"))
    (is (= (as-string n-year) "2010-10-31"))))

(deftest test-decrementing-date
  (let [d (date "2009-11-01")
        n-day (decrement-day d)
        n-month (decrement-month d)
        n-year (decrement-year d)]
    (is (= (as-string n-day) "2009-10-31"))
    (is (= (as-string n-month) "2009-10-01"))
    (is (= (as-string n-year) "2008-11-01"))))
