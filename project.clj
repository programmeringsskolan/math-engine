(defproject math-engine "0.1.0-SNAPSHOT"
  :description "App that creates math problems
                and corrects answers to them."

  :url "http://example.com/FIXME"

  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring "1.8.0"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]]

  :main ^:skip-aot math-engine.main

  ;; lein-ring is a plugin that makes it possible
  ;; to auto reload the server on code changes
  ;; https://github.com/weavejester/lein-ring
  ;; run lein ring server 8000 in your terminal
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler math-engine.main/app}

  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}})
