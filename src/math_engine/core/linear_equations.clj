(ns math-engine.core.linear-equations
  (:require
   [math-engine.core.utils :refer [string->number]]
   [clojure.string]))

;; This namespace holds all the functions
;; that handles math problems related to 
;; linear equations.

(defn create-linear-equation
  "Create a linear equation on the form
   ax + b = c
   where a, b and c are random integers between 1 and 10"
  []
  (str
   (+ 1 (rand-int 9)) "x + " (+ 1 (rand-int 9)) " = " (+ 1 (rand-int 9))))

(defn get-coefficient
  "Gets a from 'ax + b'"
  [expression]
  (->
   expression
   (clojure.string/split #"\+")
   (first)
   (clojure.string/split #"x")
   (first)
   (string->number)))

(defn get-constant
  "Gets b from 'ax + b'"
  [expression]
  (->
   expression
   (clojure.string/split #"\+")
   (second)
   (string->number)))

(defn parse-linear-equation-string
  "Turn 'ax + b = c' into {:a a :b b :c c}"
  [equation-string]
  (as-> equation-string $
    (clojure.string/split $ #"=")
    (hash-map
     :a (get-coefficient (first $))
     :b (get-constant (first $))
     :c (string->number (second $)))))

(defn get-answer
  "Get the answer from the form"
  [request]
  (->
   request
   (:form-params)
   (get "x")
   (string->number)))

(defn get-problem
  "Get the answer from the form"
  [request]
  (->
   request
   (:form-params)
   (get "math-problem")))

(defn- solve
  "Solution for ax + b = c is (c - b)/a"
  [{a :a b :b c :c}]
  (/ (- c b) a))

(defn verify-linear-equation-solution
  "Verify if the equation is correct or not"
  [request]
  (->
   request
   (get-problem)
   (parse-linear-equation-string)
   (solve)
   (= (get-answer request))
   (if
    "Rätt!"
     "Fel!")))
