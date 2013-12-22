(ns in-action.chap10_03
  (:use compojure.core
        hiccup.core))

(defroutes hello
  (GET "/" []
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body "<h1>Hello, world!</h1>"})
  (GET "/:message" [message]
       (html [:h2 "You said: " message]))
  (ANY "*" []
       {:status 404
        :header {"Content-Type" "text/html"}
        :body "Page not found!"}))
