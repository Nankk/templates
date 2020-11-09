(ns {{name}}.core
    (:require
     [reagent.dom :as rdom]
     [re-frame.core :as rf]
     [{{name}}.events :as events]
     [{{name}}.views :as views]
     [{{name}}.config :as config]
     [{{name}}.style.core :as style.core]
     ))

(defn- compile-garden []
  (println "Compiling garden...")
  (let [css-text (style.core/summarize)
        css-elem (. js/document getElementById "garden")]
    (set! (. css-elem -textContent) css-text)))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (compile-garden)
  (rdom/render [views/main-panel]
               (.getElementById js/document "app")))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
