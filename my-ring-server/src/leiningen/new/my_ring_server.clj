(ns leiningen.new.my-ring-server
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-ring-server"))

(def text-files ["src/{{sanitized}}/core.clj"
                 "src/{{sanitized}}/handler/main.clj"
                 "project.clj"])

(defn- process-text-file [data path]
  (->files data [path (render path data)]))

(defn my-ring-server [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Please kindly use --force option for 'lein new'.")
    (main/info "Generating fresh 'lein new' my-ring-server project.")
    (doseq [f text-files] (process-text-file data f))
    ;; Only .gitignore cannot be assined as the same name due to first dot.
    (->files data [".gitignore" (render "gitignore" data)])))
