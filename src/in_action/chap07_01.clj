(ns in-action.chap07_01
  (:require [clojure.walk :refer :all]))

(defn unless [test then]
  (if (not test)
    then))

(defn unless-with-fn [test then-thunk]
  (if (not test)
    (then-thunk)))

;(defn exhibits-oddity? [x]
  ;(if (odd? x)
    ;"Very odd, indeed!"))

;(defn exhibits-oddity? [x]
  ;(unless-with-fn (even? x)
    ;(fn [] "Very odd, indeed!")))

; Creating macro with lists
(defmacro unless-with-list [test then]
  (list 'if (list 'not test)
    then))

; Creating macro with templates
;(defmacro unless [test then]
  ;`(if (not ~test)
    ;~then))

(defmacro unless
  ([test then else]
  `(if (not ~test)
     ~then
     ~else))
  ([test then]
   `(if (not ~test)
      ~then)))

(defn describe-unless []
  (println
    (macroexpand-1
      '(unless (even? x) (println "Very odd, indeed!")))))

(defn exhibits-oddity? [x]
  (unless (even? x)
    "Very odd, indeed!"))

(defn exhibits-full-oddity? [x]
  (unless (even? x)
      "odd"
      "even"))

(defn addition [x y]
  (+ x y))

; Creating a macro with local variable
(defmacro def-logged-fn [fn-name args & body]
  `(defn ~fn-name ~args
     (let [now# (System/currentTimeMillis)]
       (println "[" now# "] call to" (str (var ~fn-name)))
       ~@body)))

(def-logged-fn printname [name]
  (str "hi " name))
