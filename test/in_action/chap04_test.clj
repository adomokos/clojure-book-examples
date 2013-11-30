(ns in-action.chap04-test
  (:require [clojure.test :refer :all]
            [in-action.chap04_03 :refer :all]))

(def user-1 {:login "rob" :referrer :mint.com :salary 100000})
(def user-2 {:login "kyle" :referrer :google.com :salary 90000})
(def user-3 {:login "celeste" :referrer :yahoo.com :salary 70000})
(def user-4 {:login "andy" :referrer :mint.com :salary 90000})
(def user-5 {:login "joe" :referrer :mint.com :salary 110000})

(deftest a-multi-method-test
  (testing "affiliate fee for user-1"
    (is (= 30.0 (affiliate-fee user-1))))
  (testing "affiliate fee for user-2"
    (is (= 9.0 (affiliate-fee user-2))))
  (testing "affiliate fee for user-3"
    (is (= 14.0 (affiliate-fee user-3))))
)

(deftest fee-category-calculation-test
  (testing "with 90000 is bronze"
    (is (= [:mint.com (profit-rating 0)]
           (fee-category {:salary 90000 :referrer :mint.com}))))
  (testing "with 100000 is silver"
    (is (= [:mint.com (profit-rating 1)]
           (fee-category {:salary 100000 :referrer :mint.com}))))
  (testing "with 110000 is gold"
    (is (= [:mint.com (profit-rating 2)]
           (fee-category {:salary 110000 :referrer :mint.com}))))
)

(deftest a-more-complex-defmulti-example
  (testing "affiliate fee for [:mint.com, ::bronze] user"
    (is (= 27.0 (profit-based-affiliate-fee user-4))))
  (testing "affiliate fee for [:mint.com, ::silver] user"
    (is (= 40.0 (profit-based-affiliate-fee user-1))))
  (testing "affiliate fee for [:mint.com, ::gold] user"
    (is (= 55.0 (profit-based-affiliate-fee user-5))))
)
