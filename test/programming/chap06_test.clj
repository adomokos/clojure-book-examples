(ns programming.chap06-test
  (:require [clojure.test :refer :all]))

(defprotocol Matrix
  "Protocol for working with 2d structures."
  (lookup [matrix i j])
  (update [matrix i j value])
  (rows [matrix])
  (cols [matrix])
  (dims [matrix]))

(extend-protocol Matrix
  clojure.lang.IPersistentVector
  (lookup [vov i j]
    (get-in vov [i j]))
  (update [vov i j value]
    (assoc-in vov [i j] value))
  (rows [vov]
    (seq vov))
  (cols [vov]
    (apply map vector vov))
  (dims [vov]
    [(count vov) (count (first vov))]))

(defn vov
  "Create a vector of h w-item vectors."
  [h w]
  (vec (repeat h (vec (repeat w nil)))))

(def matrix (vov 3 4))

(deftest matrix-test
  (testing "creating matrix"
    (let [nil-matrix [[nil nil nil nil]
                      [nil nil nil nil]
                      [nil nil nil nil]]]
      (is (= nil-matrix matrix)))
    (let [field-set-matrix [[nil nil nil nil]
                            [nil nil :x nil]
                            [nil nil nil nil]]]
      (is (= field-set-matrix (update matrix 1 2 :x)))
      (is (= :x (lookup field-set-matrix 1 2))))))

(defrecord NamedPoint [^String name ^long x ^long y])

;(println (NamedPoint/getBasis))
;(println (map meta (NamedPoint/getBasis)))

(defrecord Point [x y])

(deftest record-equality-test
  (testing "value equality"
    (= (Point. 2 3) (Point. 2 3))
    (= 3 3N)
    (= (Point. 3N 4N) (Point. 3N 4N))))

(extend Point
  Matrix
  {:lookup (fn [pt i j]
             (when (zero? j)
               (case i
                 0 (:x pt)
                 1 (:y pt))))
   :update (fn [pt i j value]
             (if (zero? j)
               (condp = i
                 0 (Point. value (:y pt))
                 1 (Point. (:x pt) value))
               pt))
   :rows (fn [pt]
           [[(:x pt)] [(:y pt)]])
   :cols (fn [pt]
           [[(:x pt) (:y pt)]])
   :dims (fn [pt] [2 1])})

(deftest point-based-matrix-test
  (testing "creating one"
    (let [matrix [[(Point. 0 0) (Point. 0 1) (Point. 0 2)]
                  [(Point. 1 0) (Point. 1 1) (Point. 1 2)]
                  [(Point. 2 0) (Point. 2 1) (Point. 2 2)]]]))
      (is (= nil (lookup matrix 0 0)))
      (is (= 1 (lookup (update matrix 0 0 1) 0 0))))
