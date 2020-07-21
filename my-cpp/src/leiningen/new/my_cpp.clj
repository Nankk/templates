(ns leiningen.new.my-cpp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "my-cpp"))

(defn my-cpp
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' my-cpp project.")
    (->files data
             ["include/core.h" (render "include-core.h" data)]
             ["src/CMakeLists.txt" (render "src-CMakeLists.txt" data)]
             ["src/core.cpp" (render "src-core.cpp" data)]
             ["test/CMakeLists.txt" (render "test-CMakeLists.txt" data)]
             ["test/core_Test.cpp" (render "test-core_Test.cpp" data)]
             ["CMakeLists.txt" (render "CMakeLists.txt" data)]
             ["PLEASE-EXECUTE-THIS-FIRST" (render "PLEASE-EXECUTE-THIS-FIRST" data)]
             [".gitignore" (render "gitignore" data)]
             )))
