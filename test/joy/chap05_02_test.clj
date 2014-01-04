(ns joy.chap05-02-test
  (:require [clojure.test :refer :all]
            [joy.chap05-02 :refer :all]))

(deftest vector-tests
  (testing "using `into` to combine sequences"
    (is (= [:a :b :c 0 1 2 3 4] (combining-sequences (range 5))))
    (is (= [:a :b :c 1 3 5 9] (combining-sequences '(1 3 5 9)))))
  (testing "typed vector stores primitives"
    (is (= [3 2 1] (primitive-vectors [Math/PI 2 1.3]))))
  (let [a-to-j (vec (map char (range 65 75)))]
    (testing "accessing elements in vector"
      (is (= \E (nth a-to-j 4)))
      (is (= \E (get a-to-j 4)))
      (is (= \E (a-to-j 4))))
    (testing "converting vector to sequences"
      (is (= '(\A \B \C \D \E \F \G \H \I \J) (seq a-to-j)))
      (is (= '(\J \I \H \G \F \E \D \C \B \A) (rseq a-to-j))))
    (testing "change value by `assoc`"
      (is (= '(\A \B \C \D "no longer E" \F \G \H \I \J) (assoc a-to-j 4 "no longer E")))))
  (testing "replace uses assoc internally"
    (is (= [1 :a 3 :a 3 :b] (replace {2 :a, 4 :b} [1 2 3 2 3 4])))))

(deftest matrix-tests
  (let [matrix [[1 2 3]
                [4 5 6]
                [7 8 9]]]
  (testing "getting a value in a matrix"
    (is (= 6 (get-in matrix [1 2]))))
  (testing "changing a value in a matrix"
    (is (= [[1 2 3][4 5 'x][7 8 9]] (assoc-in matrix [1 2] 'x))))
  (testing "update-in is using apply internally"
    (is (= [[1 2 3][4 5 600][7 8 9]] (update-in matrix [1 2] * 100))))))

(deftest vectors-as-stacks-tests
  (let [my-stack [1 2 3]]
    (testing "peek gives the last item"
      (is (= 3 (peek my-stack))))
    (testing "pop gives back the vector without the last item"
      (is (= [1 2] (pop my-stack))))
    (testing "conj adds an item to the end"
      (is (= [1 2 3 4] (conj my-stack 4))))
    (testing "adding up the last two items"
      (is (= 5 (+ (peek my-stack) (peek (pop my-stack))))))))

