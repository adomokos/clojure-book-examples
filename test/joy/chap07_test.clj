(ns joy.chap07-test
  (:require [clojure.test :refer :all]
            [joy.chap07 :refer :all]))

(def plays [
  {:band "Burial" :plays 979 :loved 9}
  {:band "Eno" :plays 2333 :loved 15}
  {:band "Bill Evans" :plays 979 :loved 9}
  {:band "Magma" :plays 2665 :loved 31}])

(deftest sort-by-3-keys-test
  (testing "the columns function, that grabs the values by keys"
    (is (= [979 9 "Burial"]
           ((columns [:plays :loved :band])
              {:band "Burial" :plays 979 :loved 9}))))
  (testing "when sorting plays by the 3 keys"
    (is (= '({:band "Bill Evans" :plays 979 :loved 9}
             {:band "Burial" :plays 979 :loved 9}
             {:band "Eno" :plays 2333 :loved 15}
             {:band "Magma" :plays 2665 :loved 31})
           (sort-by (columns [:plays :loved :band]) plays)))))

(deftest looking-at-pure-functions-test
  (testing "changing a value by a function for a key"
    (is (= {:band "BURIAL"}
           (keys-apply #(.toUpperCase %) #{:band} (plays 0)))))
  (testing "manipulate the collection"
    (is (= {:band "Burial" :plays 489 :loved 4}
           (manip-map #(int (/ % 2)) #{:plays :loved} (plays 0))))))

(deftest named-arguments-in-functions-test
  (testing "when both p1 and p2 are provided"
    (is (= -6.0 (slope :p1 [4 15] :p2 [3 21]))))
  (testing "when only p2 is provided"
    (is (= 0.5 (slope :p2 [2 1]))))
  (testing "when no p1 or p2 were provided"
    (is (= 1.0 (slope)))))
