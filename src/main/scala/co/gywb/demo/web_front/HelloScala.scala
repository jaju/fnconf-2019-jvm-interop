package co.gywb.demo.web_front

;

import javax.ws.rs.{GET, Path, PathParam, Produces}
import javax.ws.rs.core.MediaType;

@Path("/hello/scala")
class HelloScala {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  @Path("{name}")
  def greet(@PathParam("name") name: String) = {
    s"Hello, $name, from Scala!";
  }
}
