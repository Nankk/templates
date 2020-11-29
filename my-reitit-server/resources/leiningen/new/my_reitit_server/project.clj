(defproject {{name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [metosin/reitit "0.5.10"]]
  :main ^:skip-aot {{name}}.core
  :target-path "target/%s"
  :profiles {:dev {:dependencies [[ring/ring-mock "0.3.2"]]}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
