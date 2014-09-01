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

(deftest building-maps
  (testing "with hash-map"
    (let [built-hash-map (hash-map :a 1 :b 2 :c 3 :d 4 :e 5)]
      (is (= {:a 1 :b 2 :c 3 :d 4 :e 5} built-hash-map))))
  (testing "anything can be a key"
    (let [m {:a 1 1 :b [1 2 3] "4 5 6"}]
      (is (= [1 "4 5 6"] [(m :a) (m [1 2 3])]))))
  (testing "key-value pairs can be converted to hash"
    (is (= {:a 1 :b 2} (into {} [[:a 1] [:b 2]]))))
  (testing "your pays don't have to be explicitly grouped"
    (is (= {:a 1 :b 2} (apply hash-map [:a 1 :b 2]))))
  (testing "zipmap combines two vectors into a map"
    (is (= {:a 1 :b 2} (zipmap [:a :b] [1 2]))))
  (testing "sorted-map guarantees a sorted collection based on key"
    (is (= {:r2d 2 :thx 1138} (sorted-map :thx 1138 :r2d 2))))
)

(deftest position-finder-test
  (testing "the initial version"
    (is (= 5 (pos1 3 [:a 1 :b 2 :c 3 :d 4])))
    (is (= nil (pos1 :foo [:a 1 :b 2 :c 3 :d 4])))
    (is (= :c (pos1 3 {:a 1 :b 2 :c 3 :d 4})))
    (is (= 13 (pos1 \3 ":a 1 :b 2 :c 3 :d 4"))))
  (testing "index created for all"
    (let [vector-indexed '([0 :a][1 1][2 :b][3 2][4 :c][5 3][6 :d][7 4])]
      (is (= vector-indexed (index [:a 1 :b 2 :c 3 :d 4])))))
  (testing "the shorter version"
    (is (= '(5) (pos 3 [:a 1 :b 2 :c 3 :d 4])))
    (is (= '() (pos :foo [:a 1 :b 2 :c 3 :d 4])))
    (is (= '(:c) (pos 3 {:a 1 :b 2 :c 3 :d 4})))
    (is (= '(13) (pos \3 ":a 1 :b 2 :c 3 :d 4"))))
)
