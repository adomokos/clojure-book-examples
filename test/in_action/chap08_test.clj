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
