(ns joy.chap01)

(defn for-example []
  ;(doseq [x [:a :b] y (range 5) :when (odd? y)]
    ;(prn x y))
  (for [x [:a :b] y (range 5) :when (odd? y)]
    [x y]))
