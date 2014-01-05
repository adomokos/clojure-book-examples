(ns joy.chap05-03-test
  (:require [clojure.test :refer :all]))

(deftest list-tests
  (testing "comparing `cons` and `conj`"
    (is (= '(1 2 3) (cons 1 '(2 3))))
    (is (= '(1 2 3) (conj '(2 3) 1))))
  (testing "list as stack"
    (is (= 1 (peek '(1 2 3))))
    (is (= '(2 3) (pop '(1 2 3))))))
