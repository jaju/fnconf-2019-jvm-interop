(ns co.gywb.demo.web-front.clojure.hello
  (:require [cheshire.core :as json])
  (:import
    [javax.ws.rs GET Path PathParam Produces]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- greet [name]
  (str "Hello, " name ", from Clojure!"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(gen-class
  :name ^{Path "/hello/clojure/{name}"} co.gywb.demo.web_front.clojure.Hello
  :state state
  :init init
  :prefix "-"
  :main false
  :methods [[^{GET true Produces ["text/plain"]} greet
             [^{PathParam "name"} String]
             String]])

(defn -init []
  [[] (atom {})])

(defn -greet [_ ^String name]
  (greet name))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; This does not go well with re-eval in the REPL
(definterface Greeter (greet [^String name]))
(deftype ^{Path "/hello/clojure2/{name}"}
  HelloClojure2 []
  Greeter
  (^{GET true
     Produces ["text/plain"]}
    greet [_ ^{PathParam "name"} name] (greet name)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
