(ns in-action.chap08-test
  (:require [clojure.test :refer :all]))


(defn square [x]
  (* x x))

(defn square-all [numbers]
  (if (empty? numbers)
    ()
    (cons (square (first numbers))
          (square-all (rest numbers)))))

(defn cube [x]
  (* x x x))

(defn cube-all [numbers]
  (if (empty? numbers)
    ()
    (cons (cube (first numbers))
          (cube-all (rest numbers)))))

(deftest square-them
  (testing "square all the elements in a list"
    (is (= '(1 4 9 16 25 36) (square-all [1 2 3 4 5 6])))
    (is (empty? (square-all [])))))

(deftest cube-them
  (testing "cube all the elements in a list"
    (is (= '(1 8 27 64 125 216) (cube-all [1 2 3 4 5 6])))
    (is (empty? (cube-all [])))))
