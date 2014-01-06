(ns joy.chap06-02)

(defn xconj [t v]
  ;(println (str t " and " v))
  (cond
    (nil? t) {:val v, :L nil, :R nil}
    (< v (:val t)) {:val (:val t)
                   :L (xconj (:L t) v)
                   :R (:R t)}
    (> v (:val t)) {:val (:val t)
                   :L (:L t)
                   :R (xconj (:R t) v)}))
