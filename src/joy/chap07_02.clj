(ns joy.chap07-02)

(defn times-n [n]
    (fn [y] (* y n)))

(defn divisible [denom]
  (fn [num]
    (zero? (rem num denom))))

(defn filter-divisible [denom s]
  ;(filter (fn [num] (zero? (rem num denom))) s)) - the long way
  (filter #(zero? (rem % denom)) s))
