(ns cookbook.02-composites-test-01
  (:require [clojure.test :refer :all]
            [clojure.set :as set]
            ))

(deftest different-set-functionalities
  (testing "constructing sets"
    (are [x y] (= x y)
      #{:a :b :c} (hash-set :a :b :c)
      #{:a :b :c} (into #{} [:a :b :c])))
  (testing "adding and removing items"
    (are [x y] (= x y)
      #{:a :b :c :d} (conj #{:a :b :c} :d)
      #{:a} (disj #{:a :b :c} :b :c))
  (testing "membership"
    (are [x y] (= x y)
      false (contains? #{:red :white :green} :blue)
      true (contains? #{:red :white :green} :green)
      nil (get #{:red :white :green} :blue)
      :green (get #{:red :white :green} :green)
      nil (:blue #{:red :white :green})
      :green (:green #{:red :white :green})))
  (testing "set operations"
    (is (= #{:white :red :blue :green}
           (set/union #{:red :white} #{:white :blue} #{:blue :green})))
    (is (= #{:red :blue}
           (set/intersection #{:red :white :blue}
                             #{:red :blue :green}
                             #{:yellow :blue :red})))
    (is (= #{1} (set/difference #{1 2 3} #{2 3} #{3})))
    (is (set/subset? #{1 2} #{1 2 3})))
  ))
