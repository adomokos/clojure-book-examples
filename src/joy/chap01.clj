(ns joy.chap01)

(defn for-example []
  ;(doseq [x [:a :b] y (range 5) :when (odd? y)]
    ;(prn x y))
  (for [x [:a :b] y (range 5) :when (odd? y)]
    [x y]))

(defn r->lfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op1 a (op2 b c))))

(defn l->rfix
  ([a op b] (op a b))
  ([a op1 b op2 c] (op2 c (op1 a b))))

(def order {+ 0 - 0
            * 1 / 1})

(defn inflix3 [a op1 b op2 c]
  (if (< (get order op1) (get order op2))
    (r->lfix a op1 b op2 c)
    (l->rfix a op1 b op2 c)))

(defprotocol Concatenatable
  (cat [this other]))

(extend-type String
  Concatenatable
  (cat [this other]
    (.concat this other)))

(extend-type java.util.List
  Concatenatable
  (cat [this other]
    (concat this other)))
