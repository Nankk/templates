(ns {{name}}.events
    (:require
     [re-frame.core :as rf]
     [{{name}}.db :as db]))

(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    (println db/default-db)
    db/default-db))
