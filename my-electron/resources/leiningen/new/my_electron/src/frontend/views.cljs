(ns frontend.views
  (:require
   [re-frame.core :as rf]
   [frontend.subs :as subs]
   ))

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])]
    [:h1 (str "Hello, " name)]))
