(ns in-action.chap08-01-test
  (:require [clojure.test :refer :all]))

(defn price-with-tax [tax-rate amount]
  (-> (/ tax-rate 100)
      (+ 1)
      (* amount)))

(deftest simple-test-calculation
  (is (= 109.5 (price-with-tax 9.5 100))))

(defn with-california-taxes [prices]
  (map #(price-with-tax 9.25 %) prices))

(deftest calculating-on-a-series-of-items
  (testing "applying taxes on prices"
    (is (= '(109.25 218.5 327.75 437.0 546.25)
           (with-california-taxes [100 200 300 400 500])))))

(defn price-calculator-for-tax [state-tax]
  (fn [price]
    (price-with-tax state-tax price)))

(def price-with-ca-tax (price-calculator-for-tax 9.25))
(def price-with-ny-tax (price-calculator-for-tax 8.0))

(deftest with-generated-functions
  (testing "CA tax calculation"
    (is (= 109.25 (price-with-ca-tax 100))))
  (testing "NT tax calculation"
    (is (= 108.0 (price-with-ny-tax 100)))))

(def hundred-times (partial * 100))

(deftest testing-partial-function
  (testing "hundred-times-with-1-argument"
    (is (= 100 (hundred-times 1))))
  (testing "hundred-times-with-1-argument"
    (is (= 200 (hundred-times 1 2)))))
