(ns math-engine.page-generator
  (:require
   [hiccup.core :refer [html]]))

(def styles {:body "max-width: 700px;
                    margin: 0 auto;"

             :math-problem "background-color: #f0f0f0;
                            border-radius: 10px;
                            padding: 20px;
                            width 100%;"})

(defn build-page
  ""
  [title math-problem]
  (html
   [:body {:style (:body styles)}
    [:h1 title]
    [:div {:style (:math-problem styles)} math-problem]
    [:h2 "Din lösning"]
    [:form
     {:action "/linear-equation"
      :method "POST"}
     "x = "
     [:input {:type "hidden"
              :value math-problem
              :name "math-problem"}]
     [:input {:type "text"
              :name "x"}]
     [:input {:type "submit"} "Skicka svar"]]]))
