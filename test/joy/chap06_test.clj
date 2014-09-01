(ns joy.chap06-test
  (:require [clojure.test :refer :all]
            [joy.chap06 :refer :all]))

(def baselist (list :barnabas :adam))
(def lst1 (cons :willie baselist))
(def lst2 (cons :phoenix baselist))

(deftest structural-sharing-test
  (testing "not only equal, identical objects as well"
    (is (= (next lst1) (next lst2)))
    (is (identical? (next lst1) (next lst2)))))

(deftest building-a-tree-test
  (let [tree1 (xconj nil 5)
        tree2 (xconj tree1 3)
        tree3 (xconj tree2 6)]
    (testing "adding the initial element"
      (is (= {:val 5 :L nil :R nil} tree1)))
    (testing "adding elements to a tree with less values"
    ;(print (xseq (xconj tree1a 2)))
      (is (= {:val 5 :L {:val 3 :L nil :R nil} :R nil}
             tree2))
      (is (= {:val 5 :L {:val 3 :L {:val 2 :L nil :R nil} :R nil} :R nil}
             (xconj tree2 2))))
    (testing "adding elements to a tree with less values"
      (is (= {:val 5 :L {:val 3 :L nil :R nil} :R {:val 6 :L nil :R nil}}
             tree3)))
    (testing "adding element after a lesser and larger value was added"
      (is (= '(3 4 5 6) (xseq (xconj tree3 4)))))
    (testing "same references for trees"
      (is (identical? (:L tree2) (:L tree3))))))


(deftest investigating-laziness
  (testing "expression is not evaluated if not needed"
    (is (= :all-truthy (if-chain '() 42 true)))
    (is (= :all-truthy (and-chain true true true)))
    (is (false? (and-chain true false true)))))
