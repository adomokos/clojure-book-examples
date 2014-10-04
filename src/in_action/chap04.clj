(ns in-action.chap04)

(defn fee-amount [percentage user]
  (float (* 0.01 percentage (:salary user))))

(defmulti affiliate-fee :referrer)

(defmethod affiliate-fee :mint.com [user]
  (fee-amount 0.03 user))
(defmethod affiliate-fee :google.com [user]
  (fee-amount 0.01 user))
(defmethod affiliate-fee :default [user]
  (fee-amount 0.02 user))

(defn fee-category [user plan]
  [(:referrer user) plan])

(defmulti profit-based-affiliate-fee fee-category)
(defmethod profit-based-affiliate-fee [:mint.com :bronze] [user plan]
  (fee-amount 0.03 user))
(defmethod profit-based-affiliate-fee [:mint.com :silver] [user plan]
  (fee-amount 0.04 user))
(defmethod profit-based-affiliate-fee [:mint.com :gold] [user plan]
  (fee-amount 0.05 user))
(defmethod profit-based-affiliate-fee [:mint.com :platinum] [user plan]
  (fee-amount 0.05 user))
(defmethod profit-based-affiliate-fee [:google.com :gold] [user plan]
  (fee-amount 0.03 user))
(defmethod profit-based-affiliate-fee [:google.com :platinum] [user plan]
  (fee-amount 0.03 user))
