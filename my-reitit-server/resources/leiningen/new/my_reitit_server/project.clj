(defproject {{name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [ring-cors "0.1.13"]
                 [metosin/reitit "0.5.10"]
                 [environ "1.2.0"]]
  :plugins [[lein-environ "1.2.0"]]
  :resource-paths ["resources"]
  :main ^:skip-aot {{name}}.core
  :target-path "target/%s"
  :jvm-opts ["-Xmx512M"]
  :profiles {:dev {:dependencies [[ring/ring-mock "0.3.2"]]
                   :env          {:dev true}}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
