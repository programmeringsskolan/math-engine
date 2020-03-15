(ns math-engine.main
  (:require
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.params :refer [wrap-params]]
   [math-engine.routes :refer [routes]])
  (:gen-class))

(defn handler
  "The 'handler' handles incoming http requests"
  [request]
  (routes request))

;; In the app definition we can add different 
;; middleware functions that will be called 
;; before the request reaches our endpoint 
;; function.
(def app
  (->
   handler
   ;; Put middleware below:
   ;; FIXME: Parse request form bodies
   wrap-params))

;; The server settings configures how our server 
;; will behave. For now we will only configure 
;; what port the server will serve our app on.
(def server-settings {:port 8000})

(defn -main
  "The '-main' function is where this program starts.
   It has one purpose: To start the server!
   For now it also prints some messages to the console."
  [& args]
  (println "> Math engine is running")
  (println (str "> Visit http://localhost:" (:port server-settings)))

  ;; The 'run-jetty' function runs the server
  ;; It makes sure that our app is up and running...
  (run-jetty app server-settings))
