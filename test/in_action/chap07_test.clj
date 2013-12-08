(ns in-action.chap07-test
  (:require [clojure.test :refer :all]
            [in-action.chap07_01 :refer :all]))

(deftest for-exhibits-oddity-for-true-case
  (testing "for 1 it returns 'Very odd, indeed!'"
    (is (= "Very odd, indeed!" (exhibits-oddity? 1))))
  (testing "for 0 it returns nil"
    (is (= nil (exhibits-oddity? 2)))))

(deftest for-exhibits-oddity-for-true-and-false-case
  (testing "for 1 it returns 'odd'"
    (is (= "odd" (exhibits-full-oddity? 1))))
  (testing "for 2 it returns 'even'"
    (is (= "even" (exhibits-full-oddity? 2)))))

(deftest andriskanak
  (testing "2 + 2 = 4"
    (is (= 4 (addition 2 2))))
  (testing "4 + 4 = 8"
    (is (= 8 (addition 4 4))))
  (testing "10 + 10 = 20"
    (is (= 20 (addition 10 10)))))
