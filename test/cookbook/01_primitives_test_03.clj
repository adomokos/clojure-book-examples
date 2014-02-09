(ns cookbook.01-primitives-test-03
  (:require [clojure.test :refer :all]
            ;; [clojure.string :as str]
            [cookbook.01-primitives :refer :all]))

(deftest converting-between-strings-keywords-symbols
  (is (= 'valid? (symbol "valid?")))
  (is (= "valid?" (str 'valid?)))
  (is (= "triumph" (name :triumph)))
  (is (= ":triumph" (str :triumph)))
  (is (= :fantastic (keyword "fantastic")))
  (is (= :fantastic (keyword 'fantastic)))
  (is (= "valid?" (name :user/valid?)))
  (is (= "user" (namespace :user/valid?))))

(deftest truncating-and-rounding-numbers
  (testing "converting to int"
    (is (= 2 (int 2.0001)))
    (is (= 2 (int 2.9999))))
  (testing "using proper rounding"
    (is (= 2 (Math/round 2.0001)))
    (is (= 3 (Math/round 2.99999))))
  (testing "ceiling and floor"
    (is (= 3.0 (Math/ceil 2.0001)))
    (is (= 2.0 (Math/floor 2.9999)))))

(deftest fuzzy-comparison
  (testing "with a tolerance"
    (is (fuzzy= 0.01 10 10.00000001))
    (is (not (fuzzy= 0.01 10 10.1)))))

(deftest statistics-on-collection-of-numbers
  (testing "calculating means"
    (is (= 5/2 (mean [1 2 3 4])))
    (is (= 5.0 (mean [1 1.6 7.4 10])))
    (is (= 0 (mean []))))
  (testing "calculating median"
    (is (= 3 (median [5 2 4 1 3])))
    (is (= 5/2 (median [7 0 2 3]))))
  (testing "calculating mode"
    (is (= '(:alan) (mode [:alan :bob :alan :greg])))
    (is (= '(:smith :doe) (mode [:smith :carpenter :doe :smith :doe])))))
