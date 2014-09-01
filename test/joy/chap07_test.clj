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
