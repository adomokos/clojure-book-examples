(ns in-action.chap03_04)

; Destructuring

(defn describe-salary [person]
  (let [first (:first-name person)
        last  (:last-name person)
        annual (:salary person)]
    (println first last "earns" annual)))

(defn describe-salary-2
  [{first :first-name,
    last :last-name,
    annual :salary}]
  (println first last "earns" annual))

(defn describe-salary-3
  [{first :first-name
    last :last-name
    annual :salary
    bonus :bonus-percentage
    :or {bonus 5}}]
  (println first last "earns" annual "with a" bonus "percent bonus"))

(defn run-describe-salary2 []
  (describe-salary-2 {:first-name "Attila"
                    :last-name "Domokos"
                    :salary "30k"}))

(defn run-describe-salary-3-with-bonus []
  (describe-salary-3 {:first-name "pascal"
                      :last-name "dylan"
                      :salary 85000
                      :bonus-percentage 20}))

(defn run-describe-salary-3-with-no-bonus []
  (describe-salary-3 {:first-name "pascal"
                      :last-name "dylan"
                      :salary 85000}))

; Vector destructuring

(defn print-amounts
  [[amount-1 amount-2]]
  (println "amounts are:" amount-1 "and" amount-2))

(defn run-print-amounts []
  (print-amounts [10.95 31.45]))

(defn print-amounts-multiple
  [[amount-1 amount-2 & remaining]]
  (println "Amounts are:" amount-1 "," amount-2 "and" remaining))

(defn run-print-amounts-multiple []
  (print-amounts-multiple [10.95 31.45 22.36 2.95]))

; Nested Vector destructuring
(defn print-first-category [[[category amount] & _]]
  (println "First category was:" category)
  (println "First amount was::" amount))

(def expenses [[:books 49.95] [:coffee 4.95] [:caltrain 2.25]])

(defn run-print-first-category []
  (print-first-category expenses))
