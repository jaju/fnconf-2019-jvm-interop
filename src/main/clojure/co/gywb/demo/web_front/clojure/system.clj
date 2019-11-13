(ns co.gywb.demo.web-front.clojure.system
  (:require [clojure.tools.nrepl.server :refer
             [start-server stop-server]])
  (:import [javax.ws.rs Produces Path GET]
           [javax.inject Inject]
           [javax.sql DataSource]
           [io.vertx.axle.core Vertx]))

(defonce system-state (atom {:nrepl-server nil :datasource nil}))
(defn start-nrepl [] (start-server :bind "0.0.0.0" :port 4001))

(gen-class
  :name ^{Path "/clojure"} co.gywb.demo.web_front.clojure.ClojureDevTools
  :state state
  :init init
  :prefix "-"
  :main false
  :methods [[^{GET true
               Path "/start-nrepl"
               Produces ["text/plain"]}
             startNrepl [] String]

            [^{GET true
               Path "/stop-nrepl"
               Produces ["text/plain"]}
             stopNrepl [] String]

            [^{Inject true}
             setDataSource [javax.sql.DataSource] void]

            [^{Inject true}
             setVertx
             [io.vertx.axle.core.Vertx] void]])

(defn -init []
  [[] system-state])

(defn -startNrepl [_]
  (if (:nrepl-server @system-state)
    "Already running"
    (do (swap! system-state
               assoc :nrepl-server (start-nrepl))
        "Started")))

(defn -stopNrepl [_]
  (if-let [nrepl-server (:nrepl-server @system-state)]

    (do (stop-server nrepl-server)
        (swap! system-state
               assoc :nrepl-server nil)
        "Stopped")

    "Not running"))

(defn -setDataSource [_ ^DataSource datasource]
  (swap! system-state
         assoc :datasource datasource))

(defn -setVertx [_ ^Vertx vertx]
  (swap! system-state
         assoc :vertx vertx)
  nil)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
