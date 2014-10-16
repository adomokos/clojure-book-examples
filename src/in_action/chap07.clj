(ns in-action.chap07)

(defmacro unless [test then]
  `(if (not ~test)
     ~then))

(defn exhibit-oddity? [x]
  (unless (even? x)
    "odd"))

(defn inc-if-odd [x]
  (unless (even? x)
    (inc x)))
