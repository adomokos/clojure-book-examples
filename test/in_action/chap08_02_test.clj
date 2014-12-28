(ns in-action.chap08-02-test
  (:require [clojure.test :refer :all]))

(defn add-pair [a b]
  (+ a b))

(def inc-by-two (partial add-pair 2))

(deftest about-currying
  (testing "adding two numbers together"
    (is (= 3 (add-pair 1 2))))
  (testing "increment it by 2"
    (is (= 3 (inc-by-two 1)))
    (is (= 5 (inc-by-two 3)))))

(defn new-user [login password email]
  (fn [a & args]
    (condp = a
      :login login
      :password password
      :email email
      :authenticate (= password (first args)))))

(deftest closures-and-objects
  (testing "adding a new user"
    (let [arjun (new-user "arjun" "secret" "arjun@zololabs.com")]
      (is (= "arjun" (arjun :login)))
      (is (= "secret" (arjun :password)))
      (is (= "arjun@zololabs.com" (arjun :email))))
    (let [adi (new-user "adi" "secret" "adi@currylogic.com")]
      (is (not (adi :authenticate "blah")))
      (is (adi :authenticate "secret")))))

(defn new-class [class-name]
  (fn [command & args]
    (condp = command
      :name (name class-name))))
(defmacro defclass [class-name]
  `(def ~class-name (new-class '~class-name)))

(defclass Person)

(deftest defining-classes
  (testing "with a macro"
    (is (= "Person" (Person :name)))))
