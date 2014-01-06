(ns joy.chap06-02-test
  (:require [clojure.test :refer :all]
            [joy.chap06-02 :refer :all]))

(deftest structural-sharing-test
  (testing "sharing next part"
    (let [baselist (list :barnabas :adam)
          lst1 (cons :willie baselist)
          lst2 (cons :phoenix baselist)]
      (is (= '(:willie :barnabas :adam) lst1))
      (is (= '(:phoenix :barnabas :adam) lst2))
      (is (= (next lst1) (next lst2)))
      (is (identical? (next lst1) (next lst2)))))
         )


(deftest simple-graph-test
  (testing "setting value"
    (is (= {:val 5, :L nil, :R nil} (xconj nil 5))))
  (testing "smaller values go to the left"
    (let [tree1 (xconj nil 5)
          tree1 (xconj tree1 3)
          tree1 (xconj tree1 2)
          tree2 (xconj tree1 7)]
      (is (= {:val 5 :L {:val 3 :L {:val 2 :L nil :R nil} :R nil} :R nil}
             tree1))
      (is (= {:val 5 :L {:val 3 :L {:val 2 :L nil :R nil} :R nil} :R {:val 7 :L nil :R nil}}
          tree2))
      (is (identical? (:L tree1) (:L tree2))))))
