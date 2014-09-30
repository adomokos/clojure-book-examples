(ns in-action.chap03-test
  (:require [clojure.test :refer :all]
            [in-action.chap03 :refer :all]))

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

(deftest accessing-user-properties
  (testing "accessing username"
    (is (= "kyle" (:username (first users))))
    (is (= "rob" (:username (last users)))))
  (testing "accessing balance"
    (is (= 175.00 (:balance (first users))))))

(deftest higher-order-function-test
  (testing "a sorter that accepts sorting function for username"
    (is (= '("kyle" "rob" "zak")
           (map :username ((sorter-using :username) users)))))
  (testing "a sorter that accepts sorting function for balance"
    (is (= '("zak" "rob" "kyle")
           (map :username ((sorter-using :balance) users))))))

(deftest special-variable-test
  (testing "making variables dynamic"
    (is (= "localhost" (expense-report "2010-01-01" "2014-04-01"))))
  (binding [*db-host* "production"]
    (is (= "production" (expense-report "2010-01-01" "2014-04-01")))))

(deftest dynamic-binding-with-aop-logging-test
  (testing "calling twice with 10"
    (is (= 20 (call-twice 10))))
  (testing "calling twice with 20"
    (is (= 40 (binding [twice (with-log twice "Calling the twice function")]
                (call-twice 20)))))
  (testing "calling twice with 30"
    (is (= 60 (call-twice 30)))))

(deftest laziness-and-special-variables-test
  (testing "calling mulitply as is"
    (is (= '(10 20 30 40 50)
           (map multiply [1 2 3 4 5]))))
  (testing "changing the factor to 20"
    (is (= '(20 40 60 80 100)
           (binding [*factor* 20]
             (doall
               (map multiply [1 2 3 4 5])))))))

(deftest let-form-revisited-test
  (testing "upcasing names"
    (is (= '("JOHN" "PAUL")
           (upcased-names ["John" "Paul"])))))

(deftest vector-bindings-test
  (testing "pulls items from a vector"
    (is (= "amounts are: 10.95 31.45"
           (print-amounts [10.95 31.45]))))
  (testing "using & and :as"
    (is (= "amounts are: 10.95 31.45 and (22.36 2.95)"
           (print-amounts-multiple [10.95 31.45 22.36 2.95])))
    (is (= "amounts are: 10.95 31.45 and (22.36 2.95) as [10.95 31.45 22.36 2.95]"
           (print-all-amounts [10.95 31.45 22.36 2.95]))))
  (testing "nested vectors"
    (is (= "First category was: :books\nFirst amount was: 49.95"
           (print-first-category [[:books 49.95] [:coffee 4.95] [:caltrain 2.25]])))))

(def a-user {:first-name "pascal"
             :last-name "dylan"
             :salary 85000
             :bonus-percentage 20})

(deftest map-bindings-test
  (testing "using :or"
    (is (= "pascal dylan earns 85000 with a 20 percent bonus"
           (describe-salary-3 a-user)))
    (is (= "john smith earns 45000 with a 5 percent bonus"
           (describe-salary-3 {:first-name "john"
                               :last-name "smith"
                               :salary 45000})))))
