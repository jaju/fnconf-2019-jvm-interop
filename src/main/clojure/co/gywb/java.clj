(ns co.gywb.java)

(defmacro samify [clazz f name]
  `(reify ~clazz (~name [_ x#] (~f x#))))

(defmacro fun [f]
  `(samify java.util.function.Function ~f apply))

(defmacro pred [f]
  `(samify java.util.function.Predicate ~f test))

(comment
  (def data (range 20))

  (.collect
    (.filter
      (.stream data)
      (co.gywb.java/pred even?))
    (java.util.stream.Collectors/toList))

  (.collect
    (.map
      (.stream data)
      (co.gywb.java/fun inc))
    (java.util.stream.Collectors/toList)))
