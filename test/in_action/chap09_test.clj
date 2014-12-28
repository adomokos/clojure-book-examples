(ns in-action.chap09-test
  (:require [clojure.test :refer :all])
  (:import [java.text SimpleDateFormat]))

(defn new-expense [date-string dollars cents category merchant-name]
  {:date (.parse (SimpleDateFormat. "yyyy-MM-dd") date-string)
   :amount-dollars dollars
   :amount-cents cents
   :category category
   :merchant-name merchant-name})

(defn total-cents [e]
  (-> (:amount-dollars e)
      (* 100)
      (+ (:amount-cents e))))

(defn total-amount
  ([expenses-list]
    (total-amount (constantly true) expenses-list))
  ([pred expenses-list]
    (->> expenses-list
         (filter pred)
         (map total-cents)
         (apply +))))

(defn is-category? [e some-category]
  (= (:category e) some-category))
(defn category-is [category]
  #(is-category? % category))

(def the-expenses
  [(new-expense "2009-8-20" 21 95 "books" "amazon.com")
   (new-expense "2009-8-21" 72 43 "food" "mollie-stones")
   (new-expense "2009-8-22" 315 71 "car-rental" "avis")
   (new-expense "2009-8-23" 15 68 "books" "borders")])

(deftest testing-base-functions
  (testing "the expenses logic"
    (let [an-expense (first the-expenses)]
      (is (= 21 (:amount-dollars an-expense)))
      (is (= "books" (:category an-expense)))
      (is (= 2195 (total-cents an-expense)))
      (is (= 42577 (total-amount the-expenses)))
      (is (= 3763 (total-amount (category-is "books") the-expenses))))))

