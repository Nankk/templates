(defproject {{name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.8.1"]
                 [ring/ring-json "0.2.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.6.2"]
                 [environ "1.2.0"]
                 [camel-snake-kebab "0.4.2"]]
  :main ^:skip-aot {{name}}.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
