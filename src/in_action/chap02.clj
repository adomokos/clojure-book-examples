(ns in-action.chap02)

(defn prime? [x]
  (let [divisors (range 2 (inc (int (Math/sqrt x))))
        remainders (map #(rem x %) divisors)]
    (not (some zero? remainders))))

(defn primes-less-than [x]
  (for [x (range 2 (inc x))
          :when (prime? x)]
    x))
