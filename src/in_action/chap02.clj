(ns in-action.chap02)

(defn prime? [x]
  (let [divisors (range 2 (inc (int (Math/sqrt x))))
        remainders (map #(rem x %) divisors)]
    (not (some zero? remainders))))

(defn primes-less-than [x]
  (for [x (range 2 (inc x))
          :when (prime? x)]
    x))

(defn final-amount [principle rate time-periods]
  (* (Math/pow (+ 1 (/ rate 100)) time-periods) principle))

(defn final-amount-> [principle rate time-periods]
  (-> rate
      (/ 100)
      (+ 1)
      (Math/pow time-periods)
      (* principle)))

(defn factorial [n]
  (reduce *
          (range 1
                 (+ 1
                    n))))

(defn factorial->> [n]
  (->> n
      (+ 1)
      (range 1)
      (reduce *)))
