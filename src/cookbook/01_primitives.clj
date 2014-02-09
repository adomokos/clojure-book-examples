(ns cookbook.01-primitives)

(defn yelling? [s]
  (every? #(or (not (Character/isLetter %))
               (Character/isUpperCase %))
          s))

(defn filename [name i]
  (format "%03d-%s" i name))

(defn fuzzy= [tolerance x y]
  (let [diff (Math/abs (- x y))]
    (< diff tolerance)))

(defn mean [coll]
  (let [sum (apply + coll)
        count (count coll)]
    (if (pos? count)
      (/ sum count)
      0)))

(defn median [coll]
  (let [sorted (sort coll)
        cnt (count sorted)
        halfway (int (/ cnt 2))]
    (if (odd? cnt)
      (nth sorted halfway)
      (let [bottom (dec halfway)
            bottom-val (nth sorted bottom)
            top-val (nth sorted halfway)]
        (mean [bottom-val top-val])))))

(defn mode [coll]
  (let [freqs (frequencies coll)
        occurences (group-by second freqs)
        modes (last (sort occurences))
        modes (->> modes
                   second
                   (map first))]
    modes))
