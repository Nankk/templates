(ns {{name}}.core
    (:require [reitit.ring :as ring]
              [reitit.coercion.spec]
              [reitit.swagger :as swagger]
              [reitit.swagger-ui :as swagger-ui]
              [reitit.ring.coercion :as coercion]
              [reitit.dev.pretty :as pretty]
              [reitit.ring.middleware.muuntaja :as muuntaja]
              [reitit.ring.middleware.exception :as exception]
              [reitit.ring.middleware.multipart :as multipart]
              [reitit.ring.middleware.parameters :as parameters]
              ;; Uncomment to use
              ;; [reitit.ring.middleware.dev :as dev]
              ;; [reitit.ring.spec :as spec]
              ;; [spec-tools.spell :as spell]
              [ring.adapter.jetty :as jetty]
              [muuntaja.core :as m]
              [clojure.spec.alpha :as s]
              [clojure.java.io :as io]
              [{{name}}.handler.main :as main]))

;; App ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def app
  (ring/ring-handler
   (ring/router
    [main/routes]
    {;;:reitit.middleware/transform dev/print-request-diffs ;; pretty diffs
     ;;:validate spec/validate ;; enable spec validation for route data
     ;;:reitit.spec/wrap spell/closed ;; strict top-level validation
     :exception pretty/exception
     :data {:coercion reitit.coercion.spec/coercion
            :muuntaja m/instance
            :middleware [;; swagger feature
                         swagger/swagger-feature
                         ;; query-params & form-params
                         parameters/parameters-middleware
                         ;; content-negotiation
                         muuntaja/format-negotiate-middleware
                         ;; encoding response body
                         muuntaja/format-response-middleware
                         ;; exception handling
                         exception/exception-middleware
                         ;; decoding request body
                         muuntaja/format-request-middleware
                         ;; coercing response bodys
                         coercion/coerce-response-middleware
                         ;; coercing request parameters
                         coercion/coerce-request-middleware
                         ;; multipart
                         multipart/multipart-middleware]}})
   (ring/routes
    (swagger-ui/create-swagger-ui-handler
     {:path "/"
      :config {:validatorUrl nil
               :operationsSorter "alpha"}})
    (ring/create-default-handler))))

;; Server ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def port 3000)

(defn start []
  (jetty/run-jetty #'app {:port port, :join? false})
  (println "server running in port 3000"))

(comment
  (start))
