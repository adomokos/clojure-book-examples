(ns in-action.chap06-test
  (:require [clojure.test :refer :all]
            [in-action.chap06 :refer :all]))

(deftest playing-with-references-test
  (testing "adding two new users"
    (is (= {"amit" {:id 1
                    :login "amit"
                    :monthly-budget 1000000
                    :total-expenses 0}}
           (add-new-user "amit" 1000000)))))
    ;(is (= {"deepthi" {:id 2
                       ;:login "deepthi"
                       ;:monthly-budget 2000000
                       ;:total-expenses 0}}
           ;(add-new-user "deepthi" 2000000)))))

