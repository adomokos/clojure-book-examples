(ns cookbook.01-primitives)

(defn yelling? [s]
  (every? #(or (not (Character/isLetter %))
               (Character/isUpperCase %))
          s))

(defn filename [name i]
  (format "%03d-%s" i name))

