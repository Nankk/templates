(ns {{name}}.main.core
    (:require
     [async-interop.interop :refer-macros [<p!]]
     [cljs.core.async :as async :refer [>! <! go chan]]
     ["fs" :as fs]
     ["path" :as path]))

(defn main []
  (println "Hello world."))
