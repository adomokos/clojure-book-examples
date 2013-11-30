(ns in-action.chap03_02)

(def ^:dynamic *db-host* "localhost")

(defn expense-report [start-date end-date]
  (println *db-host*))

; Function wrapping example

(defn ^:dynamic twice [x]
  (println "original function")
  (println (* 2 x)))

(defn call-twice [y]
  (twice y))

(defn with-log [function-to-call log-statement]
  (fn [& args]
    (println log-statement)
    (apply function-to-call args)))

(defn run-twice-example []
  (call-twice 10)

  (binding [twice (with-log twice "Calling the twice function")]
    (call-twice 20))

  (call-twice 30))

; Multiply example

(def ^:dynamic *factor* 10)

(defn multiply [x]
  (* x *factor*))

(defn run-multiply-example []
  (println (map multiply (range 1 6)))
  (binding [*factor* 20]
    (doall (map multiply (range 1 6)))))

; The Let form revisited

(defn upcased-names [names]
  (let [up-case (fn [name] (.toUpperCase name))]
    (map up-case names)))

(defn run-upcased-names []
  (upcased-names ["Atti", "Detti"]))

(defn try-let []
  (let [*factor* 20]
    (println *factor*)
    (doall (map multiply (range 1 6)))))

(defn create-scaler [scale]
  (fn [x]
    (* x scale)))

(defn percent-scaler [] (create-scaler 100))
