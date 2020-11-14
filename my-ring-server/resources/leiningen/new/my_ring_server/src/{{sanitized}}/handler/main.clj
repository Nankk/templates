(ns {{name}}.handler.main
    (:require
     [compojure.core :refer [defroutes GET POST]]
     [compojure.route :as route]
     [ring.util.response :refer [resource-response response]]))

(defn- poyo [req]
  (println (pr-str req))
  (response {:value "hello"
             :body (req :body)}))

(defroutes main-routes
  (GET "/" [] "Hello World")
  (POST "/poyo" [] poyo)
  (route/resources "/")
  (route/not-found "Not Found"))
