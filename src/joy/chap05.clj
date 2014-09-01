(ns joy.chap05)

(defn neighbors
  ([size yx] (neighbors [[-1 0][1 0][0 -1][0 1]]
                        size
                        yx))
  ([deltas size yx]
    (filter (fn [new-yx]
              (every? #(< -1 %) new-yx))
            (map #(vec (map + yx %)) deltas))))

(defn pos1 [e coll]
  (let [cmp (if (map? coll)
              #(= (second %1) %2)
              #(= %1 %2))]
    (loop [s coll idx 0]
      (when (seq s)
        (if (cmp (first s) e)
          (if (map? coll)
            (first (first s))
            idx)
          (recur (next s) (inc idx)))))))

(defn index [coll]
  (cond
    (map? coll) (seq coll)
    (set? coll) (map vector coll coll)
    :else (map vector (iterate inc 0) coll)))

(defn pos [e coll]
  (for [[i v] (index coll) :when (= e v)] i))
