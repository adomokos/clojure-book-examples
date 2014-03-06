(ns cookbook.03-general-computing-protocols-test
  (:require [clojure.test :refer :all]
            [cookbook.03-general-computing-protocols :refer :all]))

(deftest extending-a-built-in-type
  (testing "string with added functionalities"
    (is (= "john" (first-name "john")))
    (is (= "smith" (last-name "john smith")))))
