(ns in-action.chap08_02
  (:use [clojure.string :only [join]]))

(defstruct expense :amount :date)
(defn log-call [id & args]
  (println "Audit - called" id "with:" (join ", " args)))

(defn ^:dynamic fetch-all-expenses [username start-date end-date]
  (log-call "fetch all" username start-date end-date)
  ;find in data-store, return list of expense structs
)

(defn expenses-greater-than [expenses threshold]
  (log-call "expenses-greater-than" threshold)
  (filter #(> (:amount %) threshold) expenses))

(defn fetch-expenses-greater-than [username start-date end-date threshold]
  (let [all (fetch-all-expenses username start-date end-date)]
    (expenses-greater-than all threshold)))
