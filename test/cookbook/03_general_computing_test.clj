(ns cookbook.03-general-computing-test
  (:require [clojure.test :refer :all]
            [cookbook.03-general-computing :refer :all]))

(deftest building-functions-with-polymorphic-behavior
  (testing "calculate triangle area"
    (is (= 4N (area {:type :triangle :base 2 :height 4})))
    (is (= 8 (area {:type :rectangle :length 2 :width 4})))
    (is (= "3.14" (format "%.2f" (area {:type :circle :radius 1})))))

  (testing "using protocols"
    (is (= 8 (protocol-area (->Rectangle 2 4)))))

  (testing "dispatching on more complex data"
    (is (= "Ingesting message {:value [1 2 3], :type :stats}, eventually..." ; This is silly, can't compare a hash
           (ingest-message {} {:type :stats :value [1 2 3]})))
    (is (= "Ingesting message {:type :heartbeat, :priority :high}, now."
           (ingest-message {} {:type :heartbeat :priority :high})))))
