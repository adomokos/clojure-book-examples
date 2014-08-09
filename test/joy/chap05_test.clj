(ns joy.chap05-test
  (:require [clojure.test :refer :all]
            [joy.chap05 :refer :all]))


(def a-to-j (vec (map char (range 65 75))))

(deftest finding-neighbors-test
  (testing "for 3x3 matrix in first cell"
    (is (= '((1 0) (0 1)) (neighbors 3 [0 0]))))
  (testing "for 3x3 matrix in second cell"
    (is (= '((1 1) (0 0) (0 2)) (neighbors 3 [0 1])))))

(deftest using-a-subvector-test
  (testing "extracting elements from 3-5"
    (is (= [\D \E \F] (subvec a-to-j 3 6)))))
