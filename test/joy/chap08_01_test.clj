(ns joy.chap08-01-test
  (:require [clojure.test :refer :all]
            [joy.chap08-01 :refer :all]))

(deftest threading-macro-test
  (testing "going through a thread of functions from the beginning"
    (is (= '(:10) (run-through 5))))
  (testing "going through a thread of functions from the end"
    (is (= '(:10) (run-from-back 5)))))

(deftest using-eval-test
  (testing "converting string to function"
    (is (= 3 (eval (list (symbol "+" ) 1 2))))))

(deftest contextual-eval-test
  (testing "expanding data into expression"
    (is (= 1001
           (contextual-eval {'a 1, 'b 2} '(let [b 1000] (+ a b)))))
    (is (= 3
           (contextual-eval {'a 1, 'b 2} '(+ a b))))))
