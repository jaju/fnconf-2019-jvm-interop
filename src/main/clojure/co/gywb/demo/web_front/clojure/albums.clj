(ns co.gywb.demo.web-front.clojure.albums
  (:require [cheshire.core :as json])
  (:import [co.gywb.demo.web_front.scala InfixCalculator]
           [co.gywb.demo.web_front.java Album]
           [co.gywb.demo.web_front.kotlin HelloAlbums]))

(defonce albums (atom #{}))

(let [infix-calculator (InfixCalculator.)]
  (defn process-infix-arithmetic [s]
    (.apply infix-calculator s)))

(defn- process-number-field [{:keys [number] :as album}]
  (assoc album
    :number (process-infix-arithmetic number)))

(defn add-album [album-json-string]
  (swap! albums conj (-> album-json-string
                         (json/parse-string keyword)
                         process-number-field)))

(defn get-albums []
  (json/generate-string @albums))

(comment
  (require '[co.gywb.demo.web-front.clojure.csv-processor :refer [read-album-data]])
  (import [co.gywb.demo.web_front.kotlin HelloAlbums])
  (defonce albums-set (read-album-data))

  (doseq [album albums-set]
    (let [a (doto (Album.)
              (.setNumber (:number album))
              (.setYear (:year album))
              (.setAlbum (:album album))
              (.setArtist (:artist album))
              (.setGenre (:genre album))
              (.setSubgenre (:subgenre album)))]
      (HelloAlbums/add a)))
  (reset! albums (into #{} (read-album-data))))
