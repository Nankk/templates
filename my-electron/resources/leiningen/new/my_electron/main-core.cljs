(ns main.core
  (:require ["electron" :refer [app BrowserWindow crashReporter dialog]]
            ["child_process" :as proc]
            ["fs" :as fs]))

(def main-window (atom nil))

(defn init-browser []
  (reset! main-window (BrowserWindow.
                       (clj->js {:webPreferences {:nodeIntegration true}
                                 :width 800
                                 :height 600})))
  (.loadURL @main-window (str "file://" js/__dirname "/public/index.html"))
  (.on @main-window "closed" #(reset! main-window nil)))

(defn main []
  (.start crashReporter
          (clj->js
           {:companyName "MyAwesomeCompany"
            :productName "MyAwesomeApp"
            :submitURL "https://example.com/submit-url"
            :autoSubmit false}))
  (.on app "window-all-closed" #(when-not (= js/process.platform "darwin")
                                  (.quit app)))
  (.on app "ready" init-browser))

(defn ^:export to-be-exported []
  ;; Declare at least one function to export.
  )

(main)
