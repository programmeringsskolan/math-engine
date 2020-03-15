(ns math-engine.core.utils
  (:require
   [clojure.string]
   [clojure.edn :as edn]))

(defn string->number
  "Make a string e.g. '123' into an integer 123"
  [number-string]
  (->
   number-string
   (clojure.string/trim)
   (edn/read-string)))
