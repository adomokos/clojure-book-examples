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

(defn find-amounts
  ([[amount-1 amount-2]]
    (str "amounts are: " amount-1 " and " amount-2)))

(defn find-amounts-with-remaining
  ([[amount-1 amount-2 & remaining]]
    (str "amounts are: " amount-1 " and " amount-2 " and " remaining)))

; Nested Vector destructuring
(defn print-first-category [[[category amount] & _]]
  (println "First category was:" category)
  (println "First amount was::" amount))

(def expenses [[:books 49.95] [:coffee 4.95] [:caltrain 2.25]])

(defn run-print-first-category []
  (print-first-category expenses))
