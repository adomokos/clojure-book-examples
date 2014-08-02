(ns joy.chap03)

(defn using-nth [name-parts]
  (str (nth name-parts 2) ", "
       (nth name-parts 0) " "
       (nth name-parts 1)))

(defn destructuring-with-vector [name-parts]
  (let [[f-name m-name l-name] name-parts]
    (str l-name ", " f-name " " m-name)))

(defn destructuring-with-map [name-map]
  (let [{f-name :f-name, m-name :m-name, l-name :l-name} name-map]
    (str l-name ", " f-name " " m-name)))

(defn using-keys [name-map]
  (let [{:keys [f-name m-name l-name]} name-map]
    (str l-name ", " f-name " " m-name)))

(defn using-or [name-map]
  (let [{:keys [title f-name m-name l-name],
         :or {title "Mr."}} name-map]
    (str title " " l-name ", " f-name " " m-name)))

(defn destructuring-in-fn-argument [{:keys [f-name m-name l-name]}]
  (str l-name ", " f-name " " m-name))
