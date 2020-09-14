(ns leiningen.new.my-node-script
  (:require [leiningen.new.templates :refer [renderer raw-resourcer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-node-script"))
(def raw (raw-resourcer "my-node-script"))

(def binary-files [])

(def text-files ["shadow-cljs.edn"
                 "resources/package.json"
                 "src/{{sanitized}}/common/const.cljs"
                 "src/{{sanitized}}/macros/async.clj"
                 "src/{{sanitized}}/macros/async.cljs"
                 "src/{{sanitized}}/main/core.cljs"])

(defn- process-text-file [data path]
  (->files data [path (render path data)]))

(defn- process-binary-file [data path]
  (->files data [path (raw path)]))

(defn my-node-script [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Please kindly use --force option for 'lein new'.")
    (main/info "Generating fresh 'lein new' my-node-script project.")
    (main/info "*Make sure to create symlink to 'resources/package.json' in the root directory*.")
    (doseq [f text-files] (process-text-file data f))
    (doseq [f binary-files] (process-binary-file data f))
    ;; Only .gitignore cannot be assined as the same name due to first dot.
    (->files data [".gitignore" (render "gitignore" data)])))
