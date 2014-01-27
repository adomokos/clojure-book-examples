(ns joy.macros)

; A basic function for asserts
;(defn assert-equals [actual expected]
  ;(when-not (= actual expected)
    ;(throw
      ;(AssertionError.
        ;(str "Expected " expected " but was " actual)))))

(defmacro assert-equals [actual expected]
  `(let [actual-value# ~actual]
     (when-not (= actual-value# ~expected)
       (throw
         (AssertionError.
           (str "Expected '" ~actual "' to be " ~expected " but was " actual-value#))))))

(defmacro dbg [x]
  `(let [x# ~x]
     (println '~x "=" x#) x#))


(def primes [0 2 3 5 7 11])

;(defn -main
  ;"I don't do a whole lot ... yet."
  ;[& args]
  ;;; work around dangerous default behaviour in Clojure
  ;(alter-var-root #'*read-eval* (constantly false))
  ;;(println (filter even? primes))
  ;(println (macroexpand-1 `(assert-equals (inc 5) 6)))
  ;(println (assert-equals (inc 5) 5))
  ;(let [x 1]
    ;(println (assert-equals (+ x 2) 3)))
  ;(println (assert-equals 2 2))
  ;(println (assert-equals 2 3)))
    ;(count (filter even? primes)) 1))

(defn pythag [x y]
  (dbg
    (* (* x x)
      (* y y))))

(defn -main
  ;"I don't do a whole lot ... yet."
  [& args]
  ; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (pythag 4 5))
