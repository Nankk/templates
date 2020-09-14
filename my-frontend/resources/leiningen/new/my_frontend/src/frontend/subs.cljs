(ns frontend.subs
  (:require
   [re-frame.core :as rf]
   ))

(def subscriptions
  {::name         [:name]
   ::ipc-channels [:ui :ipc-chennels]})

(doseq [[sub-key item] subscriptions]
  (if (coll? item)
    (rf/reg-sub sub-key
      (fn [db _] (get-in db (vec item))))
    (rf/reg-sub sub-key item)))
