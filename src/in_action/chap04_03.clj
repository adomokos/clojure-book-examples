(ns in-action.chap04_03)

(defn fee-amount [percentage user]
  (float (* 0.01 percentage (:salary user))))

(defmulti affiliate-fee :referrer)

(defmethod affiliate-fee :mint.com [user]
  (fee-amount 0.03 user))

(defmethod affiliate-fee :google.com [user]
  (fee-amount 0.01 user))

(defmethod affiliate-fee :default [user]
  (fee-amount 0.02 user))

(def profit-rating [::bronze ::silver ::gold])

; More complex example
(defn fee-category [user]
  (let [calculated-profit-rating
    (if (>= (:salary user) 100000)
      (if (>= (:salary user) 110000)
        (profit-rating 2)
        (profit-rating 1))
      (profit-rating 0))]
    [(:referrer user) calculated-profit-rating]
  )
)

(defmulti profit-based-affiliate-fee fee-category)
(defmethod profit-based-affiliate-fee [:mint.com ::bronze] [user]
  (fee-amount 0.03 user))
(defmethod profit-based-affiliate-fee [:mint.com ::silver] [user]
  (fee-amount 0.04 user))
(defmethod profit-based-affiliate-fee [:mint.com ::gold] [user]
  (fee-amount 0.05 user))

; Derive for describing hierarchies
(derive ::gold ::premier)

(defn is-gold-premier []
  (isa? ::gold ::premier))
