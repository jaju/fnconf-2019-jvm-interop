(ns co.gywb.demo.web-front.hello-clj
  (:require [cheshire.core :as json])
  (:import
    [javax.ws.rs GET Path]
    [javax.ws.rs PathParam Produces]))

(gen-class
  :name ^{Path "/hello/clojure/{name}"} co.gywb.demo.web_front.HelloClojure
  :state state
  :init init
  :prefix "-"
  :main false
  :methods [[^{GET true Produces ["application/json"]} greet [^{PathParam "name"} String] String]])

(defn -init []
  [[] (atom {})])

(defonce -this (atom nil))
(defn -greet [this ^String name]
  (reset! -this this)
  (let []
    (json/generate-string {:response         (str "Hello, " (or name "Clojure") ". Again!!")})))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(definterface Greeter (greet [^String name]))
(deftype ^{Path "/hello/clojure2/{name}"}
  HelloClojure2 []
  Greeter
  (^{GET true}
    greet [this ^{PathParam "name"} name] (str "Good morning, " (or name "Clojure") "! Once more...")))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
