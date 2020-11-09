(ns leiningen.new.my-frontend
  (:require [leiningen.new.templates :refer [renderer raw-resourcer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-frontend"))
(def raw (raw-resourcer "my-frontend"))

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

(def text-files ["src/{{sanitized}}/macros/async.clj"
                 "src/{{sanitized}}/macros/async.cljs"
                 "src/{{sanitized}}/style/global.cljs"
                 "src/{{sanitized}}/style/core.cljs"
                 "src/{{sanitized}}/db.cljs"
                 "src/{{sanitized}}/config.cljs"
                 "src/{{sanitized}}/events.cljs"
                 "src/{{sanitized}}/core.cljs"
                 "src/{{sanitized}}/views.cljs"
                 "src/{{sanitized}}/subs.cljs"
                 "src/{{sanitized}}/const.cljs"
                 "package.json"
                 "shadow-cljs.edn"
                 "resources/public/css/fontawesome.css"
                 "resources/public/css/reset.css"
                 "resources/public/css/bootstrap.min.css"
                 "resources/public/js/bootstrap.min.js"
                 "resources/public/js/jquery.min.js"
                 "resources/public/js/popper.min.js"
                 "resources/public/index.html"
                 ])

(defn- process-text-file [data path]
  (->files data [path (render path data)]))

(defn- process-binary-file [data path]
  (->files data [path (raw path)]))

(defn my-frontend [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Please kindly use --force option for 'lein new'.")
    (main/info "Generating fresh 'lein new' my-frontend project.")
    (doseq [f text-files] (process-text-file data f))
    (doseq [f binary-files] (process-binary-file data f))
    ;; Only .gitignore cannot be assined as the same name due to first dot.
    (->files data [".gitignore" (render "gitignore" data)])))
