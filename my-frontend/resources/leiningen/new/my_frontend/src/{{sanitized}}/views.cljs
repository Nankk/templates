(ns {{name}}.views
    (:require
     [re-frame.core :as rf]
     [{{name}}.subs :as subs]
     ))

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])]
    [:h1 (str "Hello, " name)]))
