(ns joy.chap06)

(defn xconj [t v]
  (cond
    (nil? t) {:val v :L nil :R nil}
    (< v (:val t)) { :val (:val t),
                     :L (xconj (:L t) v),
                     :R (:R t) }
    :else          { :val (:val t),
                     :L (:L t)
                     :R (xconj (:R t) v)} ))

(defn xseq [t]
  (when t
    (concat (xseq (:L t)) [(:val t)] (xseq (:R t)))))

(defn if-chain [x y z]
  (if x
    (if y
      (if z
        (do :all-truthy)))))

(defn and-chain [x y z]
  (and x y z (do :all-truthy)))

(defn simple-range [i limit]
  (lazy-seq
    (when (< i limit)
      (cons i (simple-range (inc i) limit)))))

(defn triangle [n]
  (/ (* n (+ n 1)) 2))

(def tri-nums (map triangle (iterate inc 1)))
