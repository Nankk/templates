(ns {{name}}.core
    (:gen-class)
    (:require [camel-snake-kebab.core :refer [->kebab-case-keyword ->snake_case]]
              [camel-snake-kebab.extras :refer [transform-keys]]
              [compojure.core :as c]
              [compojure.handler :as handler]
              [{{name}}.handler.main :as main]
              [ring.adapter.jetty :as server]
              [ring.middleware.json :refer [wrap-json-body wrap-json-response]]))

(def port 3000)

;;; Middlewares

(defn wrap-kebab-case-keys [handler]
  (fn [request]
    (let [response  (->> request
                         (transform-keys #(->kebab-case-keyword % :separator \_) ,,)
                         handler)]
      (transform-keys #(->snake_case % :separator \-) response))))

;;; App

(def app
  (-> (c/routes
       main/main-routes)
      handler/site
      wrap-kebab-case-keys
      wrap-json-body
      wrap-json-response))

;;; Server

(defonce server (atom nil))

(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'app {:port port :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))

(restart-server)
