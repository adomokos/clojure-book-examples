(ns in-action.chap08-02-test
  (:require [clojure.test :refer :all]
            [in-action.chap08_02 :refer :all]))

(defn stub-fn [return-value]
  (fn [& args]
    return-value))

(defmacro stubbing [stub-forms & body]
  (let [stub-pairs (partition 2 stub-forms)
        returns (map last stub-pairs)
        stub-fns (map #(list 'stub-fn %) returns)
        real-fns (map first stub-pairs)]
    `(binding [~@(interleave real-fns stub-fns)]
       ~@body)))

(def all-expenses
  [(struct-map expense :amount 10.0 :date "2010-02-28")
   (struct-map expense :amount 20.0 :date "2010-02-25")
   (struct-map expense :amount 30.0 :date "2010-02-21")])

(deftest test-fetch-expenses-greater-than
  (stubbing [fetch-all-expenses all-expenses]
    (let [filtered (fetch-expenses-greater-than "" "" "" 15.0)]
      (is (= (count filtered) 2))
      (is (= (:amount (first filtered)) 20.0))
      (is (= (:amount (last filtered)) 30.0)))))

(deftest test-filter-greater-than
  (stubbing [fetch-all-expenses all-expenses]
    (let [filtered (expenses-greater-than all-expenses 15.0)]
      (is (= (count filtered) 2))
      (is (= (:amount (first filtered)) 20.0))
      (is (= (:amount (last filtered)) 30.0)))))
