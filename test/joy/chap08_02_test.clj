(ns joy.chap08-02-test
  (:require [clojure.test :refer :all]
            [joy.chap08-02 :refer :all]))

(deftest testing-the-unless-macro
  (testing "with a simple example"
    ;(print (macroexpand-1 `(unless false 1 2)))
    (is (= :even (odd-or-even 4)))
    (is (= :odd (odd-or-even 3))))
  (testing "10 from 100 is 90"
    (is (= 90 (from-end (range 1 101) 10)))))
