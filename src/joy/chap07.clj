(ns joy.chap07)

(defn columns [column-names]
  (fn [row]
    (vec (map row column-names))))
