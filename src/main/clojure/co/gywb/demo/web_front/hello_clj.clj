(ns co.gywb.demo.web-front.hello-clj
  (:require [cheshire.core :as json])
  (:import
    [javax.ws.rs GET Path Produces]
    [javax.ws.rs.core MediaType]))

#_(definterface Greeter (helloClj []))

(gen-class
  :name ^{Path "/hello-clj/{name}"} co.gywb.demo.web_front.HelloClj
  :state state
  :init init
  :prefix "-"
  :main false
  :methods [[^{GET true Produces ["application/json"]} helloClj [String] String]])

#_(deftype ^{Path "/hello-clj"}
    HelloClj []
    Greeter
    (^{GET true}
      helloClj [this] "Good morning, Clojure! Once more..."))

(defn -init []
  [[] (atom {})])

(defn -helloClj [this name]
  (json/generate-string {:response (str "Hello, " (or name "Clojure") ". Again!!")}))
