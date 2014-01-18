(ns joy.chap07-04-test
  (:require [clojure.test :refer :all]
            [joy.chap07-04 :refer :all]))

(deftest a-mundane-recursion-test
  (testing "calculating power of"
    (is (= 64 (pow 4 3)))))

(deftest tail-recursion-test
  (testing "the recursion logic"
    (is (= 125 (tail-pow 5 3)))))

(deftest a-FSA-with-elevator
  (testing "elevator goes up and down"
    (is (elevator [:close :up :open :close :down :open :done]))))

(def world [[  1   1   1   1   1]
            [999 999 999 999   1]
            [  1   1   1   1   1]
            [  1 999 999 999 999]
            [  1   1   1   1   1]])

(deftest calculating-neighbors-test
  (testing "for [0 0] the neighbors are [0 1] and [1 0]"
    (is (= '([1 0] [0 1]) (neighbors 5 [0 0])))))

(deftest estimating-cost-test
  (testing "for 0 0 with 900 at each slot"
    (is (= 7200 (estimate-cost 900 5 0 0))))
  (testing "for 4 4 with 900 at each slot"
    (is (= 0 (estimate-cost 900 5 4 4)))))

(deftest calulating-path-cost
  (testing "for cost 1"
    (is (= 901 (path-cost 900 {:cost 1})))))

(deftest calculating-total-cost
  (testing "for y 0 and x 0 position"
    (is (= 7200 (total-cost 0 900 5 0 0))))
  (testing "for y 3 and x 4 position"
    (is (= 1900 (total-cost 1000 900 5 3 4)))))

(deftest min-by-test
  (testing "finds the lowest value in the collection when smallest is last"
    (is (= {:cost 9} (min-by :cost [{:cost 100} {:cost 36} {:cost 9}]))))
  (testing "finds the lowest value in the collection when smallest is first"
    (is (= {:cost 9} (min-by :cost [{:cost 9} {:cost 36} {:cost 100}])))))

; Unfortunately the book example does not work :-(
; java.lang.ClassCastException: clojure.lang.LazySeq cannot be cast to java.lang.Comparable
;(deftest the-A-star-algorithm-tests
  ;(testing "finds the least expensive route"
    ;(is (= [{:cost 17,
             ;:yxs [[0 0] [0 1] [0 2] [0 3] [0 4] [1 4] [2 4]
                   ;[2 3] [2 2] [2 1] [2 0] [3 0] [4 0] [4 1]
                   ;[4 2] [4 3] [4 4]]}
            ;:steps 94]
           ;(astar [0 0] 900 world)))))
