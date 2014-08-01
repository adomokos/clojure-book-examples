(ns joy.chap02-test
  (:require [clojure.test :refer :all]
            [joy.chap02 :refer :all]))

(deftest sum-down-test
  (testing "using recursion"
    (is (= 55 (sum-down-from 0 10))))
  (testing "using loop"
    (is (= 55 (sum-down-with-loop 10)))))

(deftest quoting-test
  (testing "unquote"
    (is (= `(+ 10 6 2) `(+ 10 ~(* 3 2) 2)))
    (is (= '(1 (2 3)) (let [x '(2 3)] `(1 ~x)))))
  (testing "unquote-splicing"
    (is (= '(1 2 3) (let [x '(2 3)] `(1 ~@x)))))
  (testing "accessing instance members"
    (is (= 10 (.-x (java.awt.Point. 10 20))))))
