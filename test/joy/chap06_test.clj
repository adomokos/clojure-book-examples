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


(deftest investigating-laziness-test
  (testing "expression is not evaluated if not needed"
    (is (= :all-truthy (if-chain '() 42 true)))
    (is (= :all-truthy (and-chain true true true)))
    (is (false? (and-chain true false true)))))

(deftest understanding-lazy-sequences-test
  (testing "using lazy-seq to create range"
    (is (= '(0 1 2 3 4 5 6 7 8) (simple-range 0 9)))))

(deftest using-infinite-sequences-test
  (testing "looking at triangles of numbers"
    (is (= 55 (triangle 10)))
    (is (= '(1 3 6 10 15 21 28 36 45 55)
           (map triangle (range 1 11)))))
  (testing "get the first 10 of tri-nums"
    (is (= '(1 3 6 10 15 21 28 36 45 55)
           (take 10 tri-nums))))
  (testing "get the first 10 even triangle numbers"
    (is (= '(6 10 28 36 66 78 120 136 190 210)
           (take 10 (filter even? tri-nums)))))
  (testing "get the 99th item"
    (is (= 5050 (nth tri-nums 99))))
  (testing "get the first 2 larger than 10,000"
    (is (= '(10011 10153) (take 2 (drop-while #(< % 10000) tri-nums))))))
