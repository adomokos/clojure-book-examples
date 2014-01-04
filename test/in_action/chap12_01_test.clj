(ns in-action.chap12_01-test
  (:require [clojure.test :refer :all]
            [in-action.chap12_01 :refer :all]))

(deftest parsing-out-the-words
  (testing "splitting a block of text by spaces"
    (let [parsed-result '(["twas" 1] ["brilling" 1] ["and" 1] ["the" 1])]
      (is (= parsed-result (parse-line "twas brilling and the"))))))
