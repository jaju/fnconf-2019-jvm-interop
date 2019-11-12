package co.gywb.demo.web_front.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/java/{name}")
    public String greet(@PathParam("name") String name) {
        return "Hello, " + name + ", from Java!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/compute")
    public String compute() {
        return "This is a complex computation";
    }
}
