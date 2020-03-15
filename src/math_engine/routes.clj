(ns math-engine.routes
  (:require [compojure.core :refer [defroutes
                                    GET
                                    POST]]
            [compojure.route :refer [not-found]]
            [math-engine.page-generator :refer [build-page]]
            [math-engine.core.linear-equations :refer [create-linear-equation
                                                       verify-linear-equation-solution]]))

;; HTTP response headers:
;; https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers
;; Content-Type:
;; https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Type
(def headers {"Content-Type" "text/html; charset=UTF-8"})

(defn create-response
  "Creates a response that the server
   sends to the client. 'Client' means
   web browser or other tool that can
   make use of HTTP responses."
  [body]
  {:stats 200 ;; HTTP status code
              ;; Read more about status codes on
              ;; https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

   :body body ;; HTTP body, usually an HTML document,
              ;; css or javascript file. JSON is also
              ;; common.

   :headers headers})


(defn send-page
  ""
  [math-problem]
  (->
   (build-page "Linjärekvationer" math-problem)
   create-response))

(def start-page "<h1>This is the start page</h1>")

(defroutes routes
  (GET "/" [] (send-page start-page))

  (GET "/linear-equation" [] (send-page (create-linear-equation)))
  (POST "/linear-equation" request (send-page (verify-linear-equation-solution request)))

  (not-found "<h1>404 Page not found</h1>"))

;; Implementera funktion som skapar problem
;; Implementera funkton som verifierar problem
