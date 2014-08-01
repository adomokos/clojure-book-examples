(ns joy.chap02-test
  (:require [clojure.test :refer :all]
            [joy.chap02 :refer :all]))

(deftest sum-down-test
  (testing "using recursion"
    (is (= 55 (sum-down-from 0 10))))
  (testing "using loop"
    (is (= 55 (sum-down-with-loop 10)))))
