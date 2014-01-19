(ns joy.chap08-01)

(defn run-through [number]
  (->>
    (* number x)
    str
    keyword
    list
    (let [x 2])))


(defn run-from-back [number]
  (->>
    (* number x)
    str
    keyword
    list
    (let [x 2])))

(defn contextual-eval [ctx expr]
  (eval
    `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
       ~expr)))
