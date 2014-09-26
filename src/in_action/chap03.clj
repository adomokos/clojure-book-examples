(ns in-action.chap03)

;; We don't get much with these two functions
;; Keywords are functions as well
(defn username [user]
  (:username user))

(defn balance [user]
  (:balance user))

(defn sorter-using [ordering-fn]
  #(sort-by ordering-fn %))

(def ^:dynamic *db-host* "localhost")

(defn expense-report [from-date to-date]
  *db-host*)
