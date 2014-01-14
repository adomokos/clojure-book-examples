(ns joy.chap07-02-test
  (:require [clojure.test :refer :all]
            [joy.chap07-02 :refer :all]))

(deftest some-closures-tests
  (testing "a function that makes functions"
    (let [times-four (times-n 4)]
      (= 40 (times-four 10)))))

(deftest number-divisible-test
  (testing "6 is divisible by 3"
    (is ((divisible 3) 6)))
  (testing "7 is NOT divisible by 3"
    (is (not ((divisible 3) 7)))))

(deftest filtering-numbers-test
  (testing "only even numbers between 0 and 10"
    (is (= '(2 4 6 8) (filter even? (range 1 10)))))
  (testing "numbers divisible by 3"
    (is (= '(3 6 9) (filter (divisible 3) (range 1 10)))))
  (testing "combining filter into a higher function"
    (is (= '(3 6 9) (filter-divisible 3 (range 1 10))))))

