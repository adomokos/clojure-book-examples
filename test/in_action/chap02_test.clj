(ns in-action.chap02-test
  (:require [clojure.test :refer :all]
            [in-action.chap02 :refer :all]))

(deftest testing-for
  (testing "creating primes"
    (is (prime? 5)))
  (testing "primes-less-than 20"
    (is (= '(2 3 5 7 11 13 17 19)
           (primes-less-than 20)))))

(deftest the-threading-macro
  (testing "calculating compound interest"
    (is (= 120.0 (final-amount 100 20 1)))
    (is (= 144.0 (final-amount 100 20 2)))
    (is (= 120.0 (final-amount-> 100 20 1)))
    (is (= 144.0 (final-amount-> 100 20 2)))))

(deftest factorial-with-thread-last
  (testing "the verbose implementation"
    (is (= 120 (factorial 5)))
    (is (= 120 (factorial->> 5)))))
