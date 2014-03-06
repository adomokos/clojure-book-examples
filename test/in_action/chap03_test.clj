(ns in-action.chap03-test
  (:require [clojure.test :refer :all]
            [in-action.chap03 :refer :all]
            [in-action.chap03_04 :refer :all]))

(def users [
    {:username "kyle"
     :balance 175.00
     :member-since "2009-04-16"}
    {:username "zak"
     :balance 12.95
     :member-since "2009-02-01"}
    {:username "rob"
     :balance 98.50
     :member-since "2009-03-30"}
])

(deftest a-higher-order-function-test
  (testing "getting a username from a hash"
    (is (= "kyle" (username (first users)))))

  (testing "getting a balance from a hash"
    (is (= 98.50 (balance (last users)))))

  (testing "sort by balance"
    (is (= ["zak", "rob", "kyle"] (map :username (sort-by-balance users)))))

  (testing "sort by name"
    (is (= ["kyle", "rob", "zak"] (map username (sort-by-name users))))))

(deftest a-find-amounts-test
  (testing "find amounts with exact args"
    (is (= "amounts are: 1 and 2" (find-amounts [1 2]))))
  (testing "find amounts with more args"
    (is (= "amounts are: 1 and 2 and (3 4)" (find-amounts-with-remaining [1 2 3 4])))))
