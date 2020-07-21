(ns {{name}}.events
  (:require
   [re-frame.core :as re-frame]
   [{{name}}.db :as db]))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    (println db/default-db)
    db/default-db))
