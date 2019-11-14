(ns co.gywb.demo.web-front.clojure.albums
  (:require [cheshire.core :as json])
  (:import [co.gywb.demo.web_front.scala InfixCalculator]
           [co.gywb.demo.web_front.java Album]))

(defonce albums (atom #{}))

(let [infix-calculator (InfixCalculator.)]
  (defn process-infix-arithmetic [s]
    (.apply infix-calculator s)))

(defn- process-number-field [{:keys [number] :as album}]
  (assoc album
    :number (-> number process-infix-arithmetic int str)))

(defn add-album [album-json-string]
  (swap! albums conj (-> album-json-string
                         (json/parse-string keyword)
                         process-number-field)))

(defn get-albums []
  (->>
    @albums
    (sort-by :year)
    json/generate-string))



















(comment
  (require '[co.gywb.demo.web-front.clojure.csv-processor
             :refer [read-album-data]])
  (defonce albums-set (read-album-data))
  (reset! albums (into #{} (read-album-data))))


























(comment
  (import [co.gywb.demo.web_front.kotlin AlbumsHTMLRenderer])
  (defn keyed-vals [m keys]
    (reduce #(conj % (%2 m)) [] keys))

  (defmacro new-album$ [vals]
    `(eval (cons 'Album. ~vals)))

  (defn new-album [album-map]
    (let [ks [:number :year
              :album :artist
              :genre :subgenre]
          vs (keyed-vals album-map ks)]
      (new-album$ vs)))

  (doseq [album albums-set]
    (-> album new-album AlbumsHTMLRenderer/add)))
