(ns joy.chap05-06-test
  (:require [clojure.test :refer :all]))

(deftest map-tests
  (let [m {:a 1 1 :b [1 2 3] "4 5 6"}]
    (testing "hash maps support heterogeneous keys"
      (is (= "4 5 6" (get m [1 2 3]))))
    (testing "for maps, keys are functions"
      (is (= :b (m 1)))))
  (testing "converting maps into seq"
    (is (= '([:a 1][:b 2]) (seq {:a 1 :b 2}))))
  (testing "converting vectors into maps"
    (is (= {:a 1 :b 2} (into {}  [[:a 1] [:b 2]]))))
  (testing "when the sequence is laid out"
    (is (= {:a 1 :b 2} (apply hash-map [:a 1 :b 2]))))
  (testing "zipmap combines the sequences"
    (is (= {:b 2 :a 1} (zipmap [:a :b][1 2])))))
