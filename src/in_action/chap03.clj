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

(defn print-amounts [[amount-1 amount-2]]
  (str "amounts are: " amount-1 " " amount-2))

(defn print-amounts-multiple [[amount-1 amount-2 & remaining]]
  (str "amounts are: " amount-1 " " amount-2 " and " remaining))

(defn print-all-amounts [[amount-1 amount-2 & remaining :as all]]
  (str "amounts are: " amount-1 " " amount-2 " and " remaining " as " all))

(defn print-first-category [[[category amount] & _]]
  (str "First category was: " category
       "\nFirst amount was: 49.95"))

(defn describe-salary-3 [{first :first-name
                          last :last-name
                          annual :salary
                          bonus :bonus-percentage :or {bonus 5}}]

