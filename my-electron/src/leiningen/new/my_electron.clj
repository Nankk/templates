(ns leiningen.new.my-electron
  (:require [leiningen.new.templates :refer [renderer raw-resourcer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-electron"))
(def raw (raw-resourcer "my-electron"))

(def binary-files ["resources/public/webfonts/fa-brands-400.woff"
                   "resources/public/webfonts/fa-solid-900.eot"
                   "resources/public/webfonts/fa-solid-900.ttf"
                   "resources/public/webfonts/fa-solid-900.svg"
                   "resources/public/webfonts/fa-regular-400.woff"
                   "resources/public/webfonts/fa-brands-400.svg"
                   "resources/public/webfonts/fa-regular-400.svg"
                   "resources/public/webfonts/fa-solid-900.woff2"
                   "resources/public/webfonts/fa-regular-400.ttf"
                   "resources/public/webfonts/fa-brands-400.woff2"
                   "resources/public/webfonts/fa-regular-400.eot"
                   "resources/public/webfonts/fa-regular-400.woff2"
                   "resources/public/webfonts/fa-solid-900.woff"
                   "resources/public/webfonts/fa-brands-400.ttf"
                   "resources/public/webfonts/fa-brands-400.eot"])

(def text-files ["src/frontend/core.cljs"
                 "src/frontend/style/core.cljs"
                 "src/frontend/style/global.cljs"
                 "src/frontend/views.cljs"
                 "src/frontend/subs.cljs"
                 "src/frontend/config.cljs"
                 "src/frontend/events.cljs"
                 "src/frontend/db.cljs"
                 "src/frontend/electron_ipc.cljs"
                 "src/common/async.cljs"
                 "src/common/async.clj"
                 "src/common/const.cljs"
                 "src/main/core.cljs"
                 "src/main/preload.cljs"
                 "shadow-cljs.edn"
                 "resources/package.json"
                 "resources/public/js/bootstrap.min.js"
                 "resources/public/js/jquery.min.js"
                 "resources/public/js/popper.min.js"
                 "resources/public/css/fontawesome.css"
                 "resources/public/css/reset.css"
                 "resources/public/css/bootstrap.min.css"
                 "resources/public/css/react_split.css"
                 "resources/public/index.html"])

(defn- process-text-file [data path]
  (->files data [path (render path data)]))

(defn- process-binary-file [data path]
  (->files data [path (raw path)]))

(defn my-electron [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Please kindly use --force option for 'lein new'.")
    (main/info "Generating fresh 'lein new' my-electron project.")
    (doseq [f text-files] (process-text-file data f))
    (doseq [f binary-files] (process-binary-file data f))
    ;; Only .gitignore cannot be assined as the same name due to first dot.
    (->files data [".gitignore" (render "gitignore" data)])))
