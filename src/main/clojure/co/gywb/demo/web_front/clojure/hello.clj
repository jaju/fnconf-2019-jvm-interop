(ns co.gywb.demo.web-front.clojure.hello
  (:require [cheshire.core :as json])
  (:import
    [javax.ws.rs GET Path PathParam Produces]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- greet-impl [name language]
  (str "Hello, " name ", from " language "!"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(gen-class
  :name ^{Path "/hello/clojure/{name}"} co.gywb.demo.web_front.clojure.Hello2
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
  (greet-impl name "Clojure"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; This one troubles by not loading greet-impl and causing runtime exception
;; Calling the gen-class implementation loads greet-impl, and this version then
;; magically runs
(definterface Greeter (greet [^String name]))
(deftype ^{Path "/hello/clojure2/{name}"}
  Hello []
  Greeter
  (^{GET true Produces ["text/plain"]}
    greet [_ ^{PathParam "name"} name]
    (greet-impl name "Clojure2")))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
