(ns joy.chap05-01-test
  (:require [clojure.test :refer :all]))

(defn neighbors
  ([size yx] (neighbors [[-1 0][1 0][0 -1][0 1]]
                        size
                        yx))
  ([deltas size yx]
   (let [first-map (map #(vec (map + yx %)) deltas)]
     (filter (fn [new-yx]
               (every? #(< -1 % size) new-yx))
                first-map))))

(def matrix [[1 2 3]
             [4 5 6]
             [7 8 9]])

(deftest finding-neighbors-test
  (testing "for 3x3 matrix"
    (is (= '((1 0)(0 1)) (neighbors 3 [0 0])))
    (is (= '([0 1][2 1][1 0][1 2]) (neighbors 3 [1 1])))))
  (testing "getting the neighbor values"
    (is (= '(4 2) (map #(get-in matrix %) (neighbors 3 [0 0])))))
