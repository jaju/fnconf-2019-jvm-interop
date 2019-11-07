(ns co.gywb.demo.web-front.hello-clj
    (:import
      [javax.ws.rs GET Path Produces]
      [javax.ws.rs.core MediaType]))

(definterface Greeter (helloClj []))

(deftype ^{Path "/hello-clj"}
         HelloClj []
         Greeter
         (^{GET true}
           helloClj [this] "Hello Clojure! Once more..."))

