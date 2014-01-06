(ns joy.chap03-03-test
  (:require [clojure.test :refer :all]
            [joy.chap03-03 :refer :all]))

(def guys-whole-name ["Guy" "Lewis" "Steele"])

(deftest destructuring-test
  (testing "creating a name from whole-name"
    (is (= "Steele, Guy Lewis" (name-builder guys-whole-name))))
  (testing "creating a name from whole-name with vector"
    (is (= "Steele, Guy Lewis" (name-builder-with-vector guys-whole-name)))))

(deftest destructuring-from-any-number-of-arguments-test
  (testing "capturing the first 3 items and rest"
    (let [[abc-value more-value] (first-three-items-and-rest (range 10))]
      (is (= "a b c are: 012" abc-value))
      (is (= "more is: (3 4 5 6 7 8 9)" more-value)))))

(def guys-name-map
  {:f-name "Guy", :m-name "Lewis" :l-name "Steele"})

(deftest destructuring-from-a-map-test
  (testing "finding values by keys"
    (is (= "Steele, Guy Lewis" (name-builder-from-map guys-name-map)))))

(deftest destructuring-with-map-like-arguments
  (testing "when both of the arguments were passed"
    (is (= [4 15] (first (find-values :p1 [4 15] :p2 [3 21]))))
    (is (= [3 21] (last (find-values :p1 [4 15] :p2 [3 21])))))
  (testing "when only the first arugment was passed"
    (is (= [4 15] (first (find-values :p1 [4 15]))))
    (is (= [1 1] (last (find-values :p1 [4 15])))))
  (testing "when no argument was passed"
    (is (= [0 0] (first (find-values))))
    (is (= [1 1] (last (find-values))))))
