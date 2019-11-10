(ns co.gywb.demo.web-front.dev
  (:require [clojure.tools.nrepl.server :refer [start-server stop-server]])
  (:import [javax.ws.rs Produces Path GET]))

(defonce clojure-tools-state (atom {:nrepl-server nil}))
(defn start-nrepl [] (start-server :bind "0.0.0.0" :port 4001))

(gen-class
  :name ^{Path "/clojure"} co.gywb.demo.web_front.ClojureDevTools
  :state state
  :init init
  :prefix "-clj-"
  :main false
  :methods [[^{GET true Path "/start-nrepl" Produces ["text/plain"]} startNrepl [] String]])

(defn -clj-init []
  [[] clojure-tools-state])

(defn -clj-startNrepl [this]
  (let [nrepl-server (:nrepl-server @(.state this))]
    (if (nil? nrepl-server)
      (do (swap! (.state this) assoc :nrepl-server (start-nrepl))
          "Started")
      "Already running")))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

