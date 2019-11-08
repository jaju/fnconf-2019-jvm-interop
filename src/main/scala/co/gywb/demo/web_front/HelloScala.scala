package co.gywb.demo.web_front;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-scala")
class HelloScala {
    @GET @Produces(Array(MediaType.TEXT_PLAIN)) def hello = {
        "Hello Scala";
    }
}
