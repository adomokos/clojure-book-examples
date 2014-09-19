(ns in-action.chap02-test
  (:require [clojure.test :refer :all]
            [in-action.chap02 :refer :all]))

(deftest testing-for
  (testing "creating primes"
    (is (prime? 5)))
  (testing "primes-less-than 20"
    (is (= '(2 3 5 7 11 13 17 19)
           (primes-less-than 20)))))

