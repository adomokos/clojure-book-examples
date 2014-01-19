(ns joy.chap08-02)

(defmacro unless [condition true-body false-body]
  `(if (not ~condition)
     ~true-body
     ~false-body
     ))

(defn odd-or-even [number]
  (unless (odd? number)
    :even
    :odd))
