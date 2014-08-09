(ns joy.chap05)

(defn neighbors
  ([size yx] (neighbors [[-1 0][1 0][0 -1][0 1]]
                        size
                        yx))
  ([deltas size yx]
    (filter (fn [new-yx]
              (every? #(< -1 %) new-yx))
            (map #(vec (map + yx %)) deltas))))
