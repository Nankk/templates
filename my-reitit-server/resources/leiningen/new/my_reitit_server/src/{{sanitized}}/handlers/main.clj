(ns {{name}}.handlers.main
    (:require [clojure.spec.alpha :as s]))

{{! Change mustache delimiter to <% and %> }}
{{=<% %>=}}
(s/def ::x int?)
(s/def ::y int?)
(s/def ::total int?)
(s/def ::math-request (s/keys :req-un [::x ::y]))
(s/def ::math-response (s/keys :req-un [::total]))

(def routes
  ["/math"
   {:swagger {:tags ["math"]}}
   ["/plus"
    {:get {:summary "plus with spec query parameters"
           :parameters {:query ::math-request}
           :responses {200 {:body ::math-response}}
           :handler (fn [{{{:keys [x y]} :query} :parameters}]
                      {:status 200
                       :body {:total (+ x y)}})}
     :post {:summary "plus with spec body parameters"
            :parameters {:body ::math-request}
            :responses {200 {:body ::math-response}}
            :handler (fn [{{{:keys [x y]} :body} :parameters}]
                       {:status 200
                        :body {:total (+ x y)}})}}]])
<%! Reset mustache delimiter %>
<%={{ }}=%>
