(ns in-action.chap08-02-test
  (:require [clojure.test :refer :all]))

(defn add-pair [a b]
  (+ a b))

(def inc-by-two (partial add-pair 2))

(deftest about-currying
  (testing "adding two numbers together"
    (is (= 3 (add-pair 1 2))))
  (testing "increment it by 2"
    (is (= 3 (inc-by-two 1)))
    (is (= 5 (inc-by-two 3)))))
