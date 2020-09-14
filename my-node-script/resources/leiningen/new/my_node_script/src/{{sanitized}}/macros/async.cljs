(ns {{name}}.macros.async
    (:require-macros [{{name}}.macros.async :as async])
    (:require [cljs.core.async :as async :refer [go chan <! >!]]))
