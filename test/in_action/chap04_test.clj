(ns in-action.chap04-test
  (:require [clojure.test :refer :all]
            [in-action.chap04 :refer :all]))

(def user-1 {:login "rob" :referrer :mint.com :salary 100000})
(def user-2 {:login "kyle" :referrer :google.com :salary 90000})
(def user-3 {:login "celeste" :referrer :yahoo.com :salary 70000})

(deftest multimethod-test
  (testing "a simpler version"
    (is (= 30.0 (affiliate-fee user-1)))
    (is (= 9.0 (affiliate-fee user-2)))
    (is (= 14.0 (affiliate-fee user-3))))
  (testing "double dispatch"
    (is (= 30.0 (profit-based-affiliate-fee user-1 :bronze)))
    (is (= 40.0 (profit-based-affiliate-fee user-1 :silver)))
    (is (= 50.0 (profit-based-affiliate-fee user-1 :gold)))
    (is (= 50.0 (profit-based-affiliate-fee user-1 :platinum)))
    (is (= 27.0 (profit-based-affiliate-fee user-2 :gold)))
    (is (= 27.0 (profit-based-affiliate-fee user-2 :platinum)))))
