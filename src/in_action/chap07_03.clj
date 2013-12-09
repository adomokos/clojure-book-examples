(ns in-action.chap07_03)

(defmacro inflix [expr]
  (let [[left op right] expr]
    (list op left right)))
