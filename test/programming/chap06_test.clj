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
