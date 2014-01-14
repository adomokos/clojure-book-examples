(ns joy.chap07-01)

(def sort-by-loved-ratio (partial sort-by #(/ (:plays %) (:loved %))))

(defn slope
  [& {:keys[p1 p2] :or {p1 [0 0] p2 [1 1]}}]
  (float (/ (- (p2 1) (p1 1))
            (- (p2 0) (p1 0)))))

(defn slope2 [p1 p2]
  {:pre [(not= p1 p2) (vector? p1) (vector? p2)]
   :post [(float? %)]}
  (slope p1 p2))
