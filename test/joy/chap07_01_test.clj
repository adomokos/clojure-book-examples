(ns joy.chap07-01-test
  (:require [clojure.test :refer :all]
            [joy.chap07-01 :refer :all]))

(deftest building-higher-order-functions-test
  (testing "a more specific sorting"
    (let [plays [{:band "Burial" :plays 979 :loved 9}
                 {:band "Eno" :plays 2333 :loved 15}
                 {:band "Bill Evans" :plays 979 :loved 9}
                 {:band "Magma" :plays 2665 :loved 31}]]
      (is (= [{:band "Magma" :plays 2665 :loved 31}
              {:band "Burial" :plays 979 :loved 9}
              {:band "Bill Evans" :plays 979 :loved 9}
              {:band "Eno" :plays 2333 :loved 15}]
             (sort-by-loved-ratio plays))))))

(deftest named-arguments-in-clojure-test
  (testing "with destructuring"
    (is (= -6.0 (slope :p1 [4 15] :p2 [3 21])))))

(deftest function-pre-and-post-condtion-test
  (testing "same values"
    (is (thrown? AssertionError (slope2 [10 10][10 10]))))
  (testing "second argument is a list"
    (is (thrown? AssertionError (slope2 [10 1] '(1 20)))))
  (testing "result is not float"
    (is (= 1.0 (slope2 [10 1] [1 20])))))
