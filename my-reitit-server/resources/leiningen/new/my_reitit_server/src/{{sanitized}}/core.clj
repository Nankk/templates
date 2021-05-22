(ns {{name}}.core
    (:gen-class)
    (:require
     [reitit.ring :as ring]
     [reitit.coercion.spec]
     [reitit.swagger :as swagger]
     [reitit.swagger-ui :as swagger-ui]
     [reitit.ring.coercion :as coercion]
     [reitit.dev.pretty :as pretty]
     [reitit.ring.middleware.muuntaja :as muuntaja]
     [reitit.ring.middleware.exception :as exception]
     [reitit.ring.middleware.multipart :as multipart]
     [reitit.ring.middleware.parameters :as parameters]
     [ring.adapter.jetty :as jetty]
     [muuntaja.core :as m]
     [clojure.spec.alpha :as s]
     [clojure.java.io :as io]
     [ring.middleware.cors :refer [wrap-cors]]
     [{{name}}.handlers.main :as main]))

;; App ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def app
  (ring/ring-handler
   (ring/router
    [["/swagger.json"
      {:get {:no-doc  true
             :swagger {:info {:title "api-docs"}}
             :handler (swagger/create-swagger-handler)}}]
     main/routes]
    {:conflicts nil
     :exception pretty/exception
     :data      {:coercion   reitit.coercion.spec/coercion
                 :muuntaja   m/instance
                 :middleware [swagger/swagger-feature
                              parameters/parameters-middleware
                              muuntaja/format-negotiate-middleware
                              muuntaja/format-response-middleware
                              #(wrap-cors % :access-control-allow-origin [#".*"]
                                          :access-control-allow-methods [:get :put :post :delete])
                              exception/exception-middleware
                              muuntaja/format-request-middleware
                              coercion/coerce-response-middleware
                              coercion/coerce-request-middleware
                              multipart/multipart-middleware]}})
   (ring/routes
    (swagger-ui/create-swagger-ui-handler
     {:path   "/"
      :url    "./swagger.json"
      :config {:validatorUrl     nil
               :operationsSorter "alpha"}})
    (ring/create-default-handler))))

;; Server ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defonce server (atom nil))
(def port 3000)

(defn stop []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn start []
  (stop)
  (reset! server (jetty/run-jetty #'app {:port port :join? false}))
  (println "server running in port " port))

(defn -main []
  (s/check-asserts true)
  (start))

(comment
  (-main))
