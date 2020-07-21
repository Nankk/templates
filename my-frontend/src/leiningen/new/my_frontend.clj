(ns leiningen.new.my-frontend
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-frontend"))

(defn my-frontend
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' my-frontend project.")
    (->files data
             ;; src
             ["src/{{sanitized}}/config.cljs" (render "config.cljs" data)]
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["src/{{sanitized}}/db.cljs" (render "db.cljs" data)]
             ["src/{{sanitized}}/events.cljs" (render "events.cljs" data)]
             ["src/{{sanitized}}/subs.cljs" (render "subs.cljs" data)]
             ["src/{{sanitized}}/views.cljs" (render "views.cljs" data)]
             ;; src (style)
             ["src/{{sanitized}}/style/core.cljs" (render "style/core.cljs" data)]
             ["src/{{sanitized}}/style/global.cljs" (render "style/global.cljs" data)]
             ;; resources
             ["resources/public/index.html" (render "index.html" data)]
             ["resources/public/css/fontawesome.css" (render "css/fontawesome.css" data)]
             ;; plain
             ["package.json" (render "package.json" data)]
             ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
             [".gitignore" (render "gitignore" data)]
             )))
