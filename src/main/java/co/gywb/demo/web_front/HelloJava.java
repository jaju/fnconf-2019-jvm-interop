package co.gywb.demo.web_front;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello/java")
public class HelloJava {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String greet(@PathParam("name") String name) {
        return "Hello, " + name + ", from Java!";
    }
}
