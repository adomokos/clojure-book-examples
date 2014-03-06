(ns cookbook.03-general-computing-protocols)

(defprotocol Person
  "Represents the name of a person."
  (first-name [person])
  (last-name [person]))

(defn first-word [s]
  (first (clojure.string/split s #" ")))

(defn second-word [s]
  (second (clojure.string/split s #" ")))

(extend String
  Person
  {:first-name first-word
   :last-name second-word})

;(extend-type String
  ;Person
  ;(first-name [s] (first (clojure.string/split s #" ")))
  ;(last-name [s] (second (clojure.string/split s #" "))))
