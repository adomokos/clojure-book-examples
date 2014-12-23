(ns in-action.chap08-test
  (:require [clojure.test :refer :all]))

(defn square [x]
  (* x x))

(defn cube [x]
  (* x x x))

(defn do-to-all [f numbers]
  (lazy-seq
    (if (empty? numbers)
      ()
      (cons (f (first numbers))
            (do-to-all f (rest numbers))))))

(deftest square-them
  (testing "square all the elements in a list"
    (is (= '(1 4 9 16 25 36) (do-to-all square [1 2 3 4 5 6])))
    (is (empty? (do-to-all square [])))
    (is (= '(1 4 9 16) (map square [1 2 3 4])))))

(deftest cube-them
  (testing "cube all the elements in a list"
    (is (= '(1 8 27 64 125 216) (do-to-all cube [1 2 3 4 5 6])))
    (is (= '(1000000000000 1000300030001)
            (take 2 (drop 10000 (do-to-all cube (range 11000))))))
    (is (empty? (do-to-all cube [])))))

;(defn total-of [numbers]
  ;(loop [nums numbers sum 0]
    ;(if (empty? nums)
      ;sum
      ;(recur (rest nums) (+ sum (first nums))))))

(defn larger-of [x y]
  (if (> x y) x y))

;(defn greatest-of [numbers]
  ;(loop [l numbers candidate (first numbers)]
    ;;(prn l numbers "-" candidate)
    ;(if (empty? l)
      ;candidate
      ;(recur (rest l) (larger-of candidate (first l))))))

(defn compute-accross [func elements value]
  (if (empty? elements)
    value
    (recur func (rest elements) (func value (first elements)))))

(defn total-of [numbers]
  (compute-accross + numbers 0))

(defn greatest-of [numbers]
  (compute-accross larger-of  numbers (first numbers)))

(deftest reducing-list-of-things
  (testing "total of a vector of numbers"
    (is (= 39 (total-of [5 7 9 3 4 1 2 8])))))

(deftest finding-greatest-of
  (testing "finding the largest element in a vector"
    (is (= 9 (greatest-of [5 7 9 3 4 1 2 8])))
    (is (nil? (greatest-of [])))))

(defn all-greater-than [threshold numbers]
  (compute-accross #(if (> %2 threshold) (conj %1 %2) %1) numbers []))

(defn all-greater-than-with-reduce [threshold numbers]
  (reduce #(if (> %2 threshold) (conj %1 %2) %1) [] numbers))

(deftest selecting-all-greater-items
  (testing "based on a threshold value"
    (is (= [7 9 8] (all-greater-than 5 [5 7 9 3 4 1 2 8])))
    (is (= [7 9 8] (all-greater-than-with-reduce 5 [5 7 9 3 4 1 2 8])))))

(defn all-lesser-than [threshold numbers]
  (compute-accross #(if (< %2 threshold) (conj %1 %2) %1) numbers []))

(defn all-lesser-than-with-reduce [threshold numbers]
  (reduce #(if (< %2 threshold) (conj %1 %2) %1) [] numbers))

(deftest selecting-all-less-than-items
  (testing "based on a threshold value"
    (is (= [3 4 1 2] (all-lesser-than 5 [5 7 9 3 4 1 2 8])))
    (is (= [3 4 1 2] (all-lesser-than-with-reduce 5 [5 7 9 3 4 1 2 8])))))

