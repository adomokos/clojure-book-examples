(ns in-action.chap08-03-test
  (:require [clojure.test :refer :all]))

(defn adder [num1 num2]
  (let [x (+ num1 num2)]
    (fn [y]
      (+ x y))))

(def add-5 (adder 2 3))

(deftest free-variables
  (testing "add-5 function"
    (is (= 15 (add-5 10)))))
