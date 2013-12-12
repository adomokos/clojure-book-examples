(ns in-action.chap08_01
  (:use [clojure.string :only [join]])
  (:import (java.text SimpleDateFormat)
           (java.util Calendar GregorianCalendar)))

(defn date [date-string]
  (let [f (SimpleDateFormat. "yyyy-MM-dd")
        d (.parse f date-string)]
    (doto (GregorianCalendar.)
      (.setTime d))))

(defn day-from [d]
  (let [converted-date (date d)]
    (.get converted-date Calendar/DAY_OF_MONTH)))

(defn month-from [d]
  (let [converted-date (date d)]
    (+ 1 (.get converted-date Calendar/MONTH))))

(defn year-from [d]
  (let [converted-date (date d)]
    (.get converted-date Calendar/YEAR)))

(defn pad [n]
  (if (< n 10) (str "0" n) (str n)))

(defn as-string [date]
  (let [y (year-from date)
        m (pad (month-from date))
        d (pad (day-from date))]
    (join "-" [y m d])))
