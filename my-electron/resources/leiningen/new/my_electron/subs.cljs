(ns {{name}}.subs
    (:require
     [re-frame.core :as rf]
     ))

(rf/reg-sub
  ::name
  (fn [db _]
    (db :name)))
