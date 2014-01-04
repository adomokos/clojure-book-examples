(ns joy.chap05-02)

(defn combining-sequences [another]
  (let [initial-vector [:a :b :c]]
    (into initial-vector another)))

(defn primitive-vectors [source-vector]
  (into (vector-of :int) source-vector))
