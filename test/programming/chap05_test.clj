(ns programming.chap05-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.walk :as walk]))

(defmacro reverse-it
  [form]
  (walk/postwalk #(if (symbol? %)
                              (symbol (str/reverse (name %)))
                              %)
                  form))

(defmacro hello
  [name]
  `(str "hello " ~name))

(defmacro my-while [test & body]
  (list 'loop []
    (concat (list 'when test) body
      '((recur)))))

(defmacro my-while-brief [test & body]
  `(loop []
    (when ~test
      ~@body
      (recur))))

(deftest reverse-expression-test
  (testing "reverses expression and evaluates it"
    (is (= "hello world"
           (reverse-it (rts "hello " "world"))))))

(deftest trying-syntax-test
  (testing "why do we use list"
    ;(macroexpand-1 '(hello "John"))
    (is (= "hello John"
           (hello "John")))))

(def a (atom 10))

(macroexpand '(my-while-brief (pos? @a) (do (println @a) (swap! a dec))))

;(deftest my-while-test
  ;(testing "with list which is noisy"
    ;(is (= 3 (my-while #(= 3 %) (inc 1))))))
