(ns leiningen.new.my-electron
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-electron"))

(defn my-electron
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' my-electron project.")
    (->files data
             ;; src (main)
             ["src/main/core.cljs" (render "main-core.cljs" data)]
             ;; src (renderer)
             ["src/{{sanitized}}/config.cljs" (render "config.cljs" data)]
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["src/{{sanitized}}/db.cljs" (render "db.cljs" data)]
             ["src/{{sanitized}}/events.cljs" (render "events.cljs" data)]
             ["src/{{sanitized}}/subs.cljs" (render "subs.cljs" data)]
             ["src/{{sanitized}}/views.cljs" (render "views.cljs" data)]
             ;; src (renderer/style)
             ["src/{{sanitized}}/style/core.cljs" (render "style/core.cljs" data)]
             ["src/{{sanitized}}/style/global.cljs" (render "style/global.cljs" data)]
             ;; resources
             ["resources/public/index.html" (render "index.html" data)]
             ["resources/public/css/bootstrap.min.css" (render "css/bootstrap.min.css" data)]
             ["resources/public/css/fontawesome.css" (render "css/fontawesome.css" data)]
             ["resources/package.json" (render "package.json" data)]
             ;; plain
             ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
             [".gitignore" (render "gitignore" data)]
             )))
