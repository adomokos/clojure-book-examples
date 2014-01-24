(ns joy.chap08-02)

(defmacro unless [condition true-body false-body]
  ;(println condition)
  ;(println true-body)
  `(if (not ~condition)
     ~true-body
     ~false-body
     ))

(defn odd-or-even [number]
  (unless (odd? number)
    :even
    :odd))

(defn from-end [s n]
  (let [delta (dec (- (count s) n))]
    (unless (neg? delta)
      (nth s delta)
      nil)))
