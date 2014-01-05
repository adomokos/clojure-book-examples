(ns joy.chap05-05-test
  (:require [clojure.test :refer :all]
   :require [clojure.set :only [:intersection :union]]))

(deftest set-tests
  (testing "returns the matched element"
    (is (= :a (#{:a :b :c :d} :a))))
  (testing "or nil if nothing found"
    (is (= nil (#{:a :b :c :d} :e))))
  (testing "returns the element if found"
    (is (= :a (get #{:a :b :c :d} :a))))
  (testing "or nil if not found"
    (is (= nil (get #{:a :b :c :d} :no-element))))
  (testing "some returns the first match"
    (is (= :b (some #{:b} #{:a 1 :b 2})))
    (is (= 1 (some #{1 :b} #{:a 1 :b 2}))))
  (testing "sorted-set keeps 'em sorted"
    (is (= #{:a :b :c} (sorted-set :b :c :a))))
    (is (= #{[1 2] [3 4]} (sorted-set [3 4] [1 2])))
  (testing "clojure.set/intersection finds the common elements"
    (is (= #{:a} (clojure.set/intersection #{:a :b} #{:a :c}))))
  (testing "clojure.set/union"
    (is (= #{:a :b :c} (clojure.set/union #{:a :b} #{:a :c}))))
  (testing "clojure.set/difference is really relative complement"
    (is (= #{:a :b} (clojure.set/difference #{:a :b :c :d} #{:c :d :e :f})))))
