(ns joy.chap07-01)

(def sort-by-loved-ratio (partial sort-by #(/ (:plays %) (:loved %))))
