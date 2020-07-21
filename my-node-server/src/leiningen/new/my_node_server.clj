(ns leiningen.new.my-node-server
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-node-server"))

(defn my-node-server
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' my-node-server project.")
    (->files data
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["resources/package.json" (render "package-inside.json" data)]
             ["resources/.gitignore" (render "gitignore-inside" data)]
             ["resources/package.json" (render "package.json" data)]
             ["resources/package-lock.json" (render "package-lock.json" data)]
             ["resources/deploy.sh" (render "deploy.sh" data)]
             ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
             [".gitignore" (render "gitignore" data)]
             )))
