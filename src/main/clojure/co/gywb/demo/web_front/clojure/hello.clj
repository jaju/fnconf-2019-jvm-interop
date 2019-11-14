(ns co.gywb.demo.web-front.clojure.hello
  (:import
    [javax.ws.rs GET Path PathParam Produces]))

(defn greet-impl [name]
  (str "Hello, " name ", from Clojure!"))

(gen-class
  :name
  ^{Path "/hello/clojure"} co.gywb.demo.web_front.clojure.Hello
  :state state
  :prefix "-"
  :methods [[^{GET true
               Path "/{name}"
               Produces ["text/plain"]}
             greet [^{PathParam "name"} String] String]])

(defn -greet [_ ^String name]
  (greet-impl name))
