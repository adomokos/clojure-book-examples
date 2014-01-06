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
