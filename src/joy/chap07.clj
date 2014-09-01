(ns joy.chap07)

(defn columns [column-names]
  (fn [row]
    (vec (map row column-names))))

(defn keys-apply [f ks m]
  (let [only (select-keys m ks)]
    (zipmap (keys only)
      (map f (vals only)))))

(defn manip-map [f ks m]
  (merge m (keys-apply f ks m)))

(defn slope
  [& {:keys [p1 p2] :or {p1 [0 0] p2 [1 1]}}]
  (float (/ (- (p2 1) (p1 1))
            (- (p2 0) (p1 0)))))
