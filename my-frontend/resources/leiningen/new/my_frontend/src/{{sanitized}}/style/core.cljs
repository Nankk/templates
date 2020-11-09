(ns {{name}}.style.core
    (:require
     [{{name}}.style.global :as global]
     ))

(defn- appendln [& ss]
  (str (apply str ss) "\n\n"))

(defn summarize []
  (-> ""
      (appendln (global/css))
      ))
