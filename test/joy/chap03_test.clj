(ns joy.chap03-test
  (:require [clojure.test :refer :all]
            [joy.chap03 :refer :all]))

(def guys-whole-name ["Guy" "Lewis" "Steele"])

(deftest destructuring-with-vector-test
  (testing "the hard way"
    (is (= "Steele, Guy Lewis" (using-nth guys-whole-name))))
  (testing "destructuring with a vector"
    (is (= "Steele, Guy Lewis" (destructuring-with-vector guys-whole-name)))))

(def guys-name-map
  {:f-name "Guy" :m-name "Lewis" :l-name "Steele"})

(deftest destructuring-with-map-test
  (testing "assembling from map"
    (is (= "Steele, Guy Lewis" (destructuring-with-map guys-name-map))))
  (testing "using keys"
    (is (= "Steele, Guy Lewis" (using-keys guys-name-map))))
  (testing "using or if segment was not found"
    (is (= "Mr. Steele, Guy Lewis" (using-or guys-name-map)))))
