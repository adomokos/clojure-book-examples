(ns in-action.chap03)

(defn username [user]
  (user :username))

(defn balance [user]
  (user :balance))

(defn sorter-using
  [ordering-fn users]
    (sort-by ordering-fn users))

;(defn sort-by-balance
  ;[users]
  ;(sort-by balance users))

(defn sort-by-balance
  [users]
  (sorter-using balance users))

(defn sort-by-name
  [users]
  (sorter-using username users))
