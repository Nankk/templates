(ns {{name}}.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [{{name}}.subs :as subs]
   [{{name}}.events :as events]
   ))

(defn main-panel []
  (let [name @(re-frame/subscribe [::subs/name])]
    [:h1 (str "Hello, " name)]))
