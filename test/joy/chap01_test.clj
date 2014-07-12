(ns joy.chap01-test
  (:require [clojure.test :refer :all]
            [joy.chap01 :refer :all]))

(deftest a-for-iterator
  (testing "returns a data structure"
    (is (= '([:a 1] [:a 3] [:b 1][:b 3]) (for-example)))))
