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


(defn ^:dynamic twice [x]
  ;;(println "original function")
    (* 2 x))

(defn call-twice [y]
  (twice y))

(defn with-log [function-to-call log-statement]
  (fn [& args]
    ;; (println log-statement)
    (apply function-to-call args)))

(def ^:dynamic *factor* 10)
(defn multiply [x]
  (* x *factor*))

(defn upcased-names [names]
  (let [up-case (fn [name]
                  (.toUpperCase name))]
    (map up-case names)))

