(ns in-action.chap10-01-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str :only :join]))

(defstruct expense :amount :date)
(defn log-call [id & args]
  (println "Audit - called" id "with:" (str/join ", " args))
  ;; do logging to some audit data store
)

(defn fetch-all-expenses [username start-date end-date]
  (log-call "fetch-all" username start-date end-date)
  ;;find in data-store, return list of expense structs
)

(defn expenses-greater-than [expenses threshold]
  (log-call "expenses-greater-than" threshold)
  (filter #(> (:amount %) threshold) expenses))

(defn fetch-expenses-greater-than [username start-date end-date threshold]
  (let [all (fetch-all-expenses username start-date end-date)]
    (expenses-greater-than all threshold)))

(def all-expenses [{:amount 10.0 :date "2010-02-28"}
                   {:amount 20.0 :date "2010-02-25"}
                   {:amount 30.0 :date "2010-02-21"}])

(defn stub-fn [return-value]
  (fn [& args]
    return-value))
(defmacro stubbing [stub-forms & body]
  (let [stub-pairs (partition 2 stub-forms)
        return (map last stub-pairs)
        stub-fns (map #(list 'stub-fn %) return)
        real-fns (map first stub-pairs)]
    `(with-redefs [~@(interleave real-fns stub-fns)]
       ~@body)))

;(defn calc-x [x1 x2]
  ;(* x1 x2))
;(defn calc-y [y1 y2]
  ;(/ y2 y1))
;(defn some-client []
  ;(println (calc-x 2 3) (calc-y 3 4)))

;;; To investigate macroexpand
;(stubbing [calc-x 1
           ;calc-y 2]
          ;(some-client))

(deftest test-fetch-expenses-greater-than
  (stubbing [fetch-all-expenses all-expenses]
    (let [filtered (fetch-expenses-greater-than "" "" "" 15.0)]
      (is (= (count filtered) 2))
      (is (= (:amount (first filtered)) 20.0))
      (is (= (:amount (last filtered)) 30.0)))))

(deftest test-filter-greater-than
  (let [fetched [(struct-map expense :amount 10.0 :date "2010-02-28")
                 (struct-map expense :amount 20.0 :date "2010-02-25")
                 (struct-map expense :amount 30.0 :date "2010-02-21")]
        filtered (expenses-greater-than fetched 15.0)]
    (is (= (count filtered) 2))
    (is (= (:amount (first filtered)) 20.0))
    (is (= (:amount (last filtered)) 30.0))))
