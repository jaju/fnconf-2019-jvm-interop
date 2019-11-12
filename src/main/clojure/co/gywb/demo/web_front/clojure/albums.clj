(ns co.gywb.demo.web-front.clojure.albums
  (:require [cheshire.core :as json])
  (:import [co.gywb.demo.web_front.scala InfixCalculator]))

(deftype Album [number year album artist genre subgenre])

(defonce albums (atom #{}))

(defn add-album [album-json-string]
  (let [input (json/parse-string album-json-string keyword)
        number (:number input)
        transformed-number (.apply (InfixCalculator.) number)
        transformed-input (assoc input :number (-> transformed-number int str))]
    (swap! albums conj transformed-input)))

(defn get-albums []
  (json/generate-string @albums))

(comment
  (require '[co.gywb.demo.web-front.clojure.csv-processor :refer [read-album-data]])
  (reset! albums (into #{} (read-album-data))))
