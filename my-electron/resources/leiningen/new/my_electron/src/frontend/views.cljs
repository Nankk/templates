(ns frontend.views
  (:require
   [re-frame.core :as rf]
   [reagent.core :as reagent]
   [frontend.subs :as subs]
   [frontend.events :as events]))

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])]
    [:h1 (str "Hello, " name)]))
