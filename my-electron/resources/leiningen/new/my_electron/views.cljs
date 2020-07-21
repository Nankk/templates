(ns {{name}}.views
    (:require
     [re-frame.core :as rf]
     [reagent.core :as reagent]
     [{{name}}.subs :as subs]
     [{{name}}.events :as events]
     ["electron" :as electron]))

(def main (. electron/remote require "./main"))

(defn open-dialog-sample []
  (let [dialog (. electron/remote -dialog)
        options (clj->js {:properties ["openFile"]
                          :title "Select file"
                          :defaultPath "."
                          :filters [{:name "Text file" :extensions ["txt"]}
                                    {:name "All files" :extensions ["*"]}]})]
    (-> (. dialog showOpenDialog nil options)
        (.then (fn [res]
                 (let [path ((js->clj (. res -filePaths)) 0)]
                   (println (str "You selected " path "."))))))))

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])]
    [:h1 (str "Hello, " name)]))
