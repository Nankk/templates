(ns {{name}}.style.global
    (:require
     [garden.core :as g]
     ))

(defn css []
  (g/css
   [:.justify {:display         "flex"
               :justify-content "space-between"}]
   [:.inline {:display        "inline-block"
              :vertical-align "middle"}]
   [:.card-header {:padding "5px 8px"}]
   [:.card-body {:padding "5px 8px"}]
   [:.code {:font-family "Monospace"}]
   [:.rounded {:border-radius "5px"}]
   [:&::-webkit-scrollbar {:width "10px"}
    [:* {:background "transparent"}]]
   [:&::-webkit-scrollbar-thumb {:background      "#dddddd"
                                 :border          "2px solid transparent"
                                 :background-clip "content-box"
                                 :border-radius   "10px"
                                 :box-shadow      "none"}]
   [:.see-through {:color            "#444444"
                   :background-color "transparent"
                   :border-radius    0
                   :border           "none"
                   :border-bottom    (str "1px solid " "#444444")}]
   [:.num {:text-align "right"
           :padding    "0px 7px"}]

   ;; For some reason these settings enables scrollbar *of the nested content inside the Pane*
   [:.Pane1 {:overflow "auto"}]
   [:.Pane2 {:overflow "auto"}]
   ))
