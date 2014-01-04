(ns joy.chap03-03)

(defn name-builder [whole-name]
  (str (nth whole-name 2) ", "
       (nth whole-name 0) " "
       (nth whole-name 1)))

(defn name-builder-with-vector [whole-name]
  (let [[f-name m-name l-name] whole-name]
    (str l-name ", " f-name " " m-name)))

(defn first-three-items-and-rest [input]
  (let [[a b c & more] input]
    [(str "a b c are: " a b c)
     (str "more is: " more)]))

(defn name-builder-from-map [name-map]
  ;(let [{f-name :f-name m-name :m-name l-name :l-name} name-map]
  (let [{:keys [f-name m-name l-name]} name-map]
    (str l-name ", " f-name " " m-name)))
