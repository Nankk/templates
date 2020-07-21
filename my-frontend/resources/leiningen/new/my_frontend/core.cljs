(ns {{name}}.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [{{name}}.events :as events]
   [{{name}}.views :as views]
   [{{name}}.config :as config]
   [{{name}}.style.core :as style.core]))

(defn- compile-garden []
  (println "Compiling garden...")
  (let [css-text (style.core/summarize)
        css-elem (. js/document getElementById "garden")]
    (set! (. css-elem -textContent) css-text)))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (compile-garden)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
