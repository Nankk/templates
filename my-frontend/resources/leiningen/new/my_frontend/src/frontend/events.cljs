(ns frontend.events
  (:require
   [re-frame.core :as rf]
   [frontend.db :as db]
   ))

(def handlers
  {::initialize-db   (fn [_ [_ _]] db/default-db)})

(doseq [[ev-key item] handlers]
  (if (coll? item)
    (rf/reg-event-db ev-key
      (fn [db [_ v]] (assoc-in db (vec item) v)))
    (rf/reg-event-db ev-key item)))
