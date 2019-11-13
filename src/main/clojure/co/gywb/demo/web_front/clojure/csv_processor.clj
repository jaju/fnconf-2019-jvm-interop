(ns co.gywb.demo.web-front.clojure.csv-processor
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as s]))

(defn docs:vecs->maps
  ([doc-vecs-with-header]
   (apply docs:vecs->maps
          ((juxt first rest) doc-vecs-with-header)))
  ([header-vec doc-vecs]
   (map zipmap (->> header-vec
                    (map (comp keyword s/lower-case))
                    repeat)
        doc-vecs)))

(defn read-album-data []
  (->> "albumlist.csv"
       io/resource
       slurp
       csv/read-csv
       docs:vecs->maps))
