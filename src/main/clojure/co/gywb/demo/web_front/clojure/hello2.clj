(ns co.gywb.demo.web-front.clojure.hello2
  (:require [co.gywb.demo.web-front.clojure.hello :refer [greet-impl]])
  (:import [javax.ws.rs PathParam Produces GET Path]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(definterface Greeter (greet [^String name]))
(deftype ^{Path "/hello/clojure2/{name}"}
  Hello2 []
  Greeter
  (^{GET true Produces ["text/plain"]}
    greet [_ ^{PathParam "name"} name]
    (greet-impl name)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

