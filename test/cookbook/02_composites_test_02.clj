(ns cookbook.02-composites-test-02
  (:require [clojure.test :refer :all]))

(deftest maps-related-operations
  (testing "converting to sequences"
    (is (= [[:c 3][:b 2][:a 1]]
           (seq {:a 1 :b 2 :c 3}))))
  (testing "using zipmap"
    (is (= {:c 3 :b 2 :a 1}
           (zipmap [:a :b :c][1 2 3]))))
  (testing "merging maps"
    (let [arizona-bird-counts {:cactus-wren 8}
          florida-bird-counts {:gull 20 :pelican 14}]
      (is (= {:pelican 14 :cactus-wren 8 :gull 20}
             (merge arizona-bird-counts florida-bird-counts)))))
  (testing "merging by adding values"
    (let [florida-bird-counts {:gull 20 :pelican 1 :egre 4}
          california-bird-counts {:gull 12 :pelican 4 :jay 3}]
      (is (= {:pelican 5 :gull 32 :egre 4 :jay 3}
             (merge-with + california-bird-counts florida-bird-counts))))))

(deftest sorting-test
  (testing "using compare"
    (is (= 1 (compare 5 2)))
    (is (= -1 (compare 0.5 1)))
    (is (= 0 (compare 1/4 0.25))))
  (testing "sorting"
    (is (= '(4 3 2 1)
           (sort > [1 4 3 2])))
    (is (= '(1 2 3 4)
           (sort < [1 4 3 2])))))
