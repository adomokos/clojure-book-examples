(ns in-action.chap07-test
  (:require [clojure.test :refer :all]
            [in-action.chap07 :refer :all]))

(deftest trying-a-macro
  (testing "tell me if the number is odd"
    (is (= "odd" (exhibit-oddity? 11)))
    (is (= nil (exhibit-oddity? 12)))))
