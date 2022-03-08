(ns leiningen.new.my-python
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-python"))

(def text-files ["{{sanitized}}/__init__.py"
                 "setup.py"
                 "readme.md"])

(defn- process-text-file [data path]
  (->files data [path (render path data)]))

(defn my-python [name]
  (let [data {:name      name
              :sanitized (name-to-path name)}]
    (main/info "Please kindly use --force option for 'lein new'.")
    (main/info "Generating fresh 'lein new' my-python project.")
    (doseq [f text-files] (process-text-file data f))
    ;; Only .gitignore cannot be assined as the same name due to first dot.
    (->files data [".gitignore" (render "gitignore" data)])))
