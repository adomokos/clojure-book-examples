(ns joy.chap01-test
  (:require [clojure.test :refer :all]
            [joy.chap01 :refer :all]))

(deftest a-for-iterator
  (testing "returns a data structure"
    (is (= '([:a 1] [:a 3] [:b 1][:b 3]) (for-example)))))

(deftest evaluating-right-to-left-converter
  (testing "with 2 arguments"
    (is (= 3 (r->lfix 1 + 2))))
  (testing "with 3 arguments"
    (is (= 6 (r->lfix 1 + 2 + 3))))
  (testing "with 3 arguments and multiplication"
    (is (= 7 (r->lfix 1 + 2 * 3))))
  (testing "with 3 arguments with multiply first"
    (is (= 23 (l->rfix 10 * 2 + 3))))
  (testing "with 3 arguments with the same function"
    (is (= 7 (inflix3 1 + 2 * 3)))
    (is (= 23 (inflix3 10 * 2 + 3)))))

(deftest adding-number-together
  (testing "sum of items in list"
    (is (= 55 (+ 1 2 3 4 5 6 7 8 9 10))))
  (testing "sum of vector elements"
    (is (= 55 (apply + [1 2 3 4 5 6 7 8 9 10])))))

(deftest comparing-numbers-in-list
  (testing "the next item is greater than the previous"
    (is (< 0 1 2 3 4 5 6 7 8 9)))
  (testing "the 3rd item is less than the previous"
    (is (not (< 0 1 -2 3 4 5 6 7 8 9)))))


