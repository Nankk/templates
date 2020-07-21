(ns my-reveal.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [my-reveal.subs :as subs]
   [my-reveal.events :as events]
   ))

(defn main-panel []
  (let [name @(re-frame/subscribe [::subs/name])]
    [:h1 (str "Hello, " name)]))
