package co.gywb.demo.web_front.scala

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, PathParam, Produces};

@Path("/hello/scala")
class Hello {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  @Path("{name}")
  def greet(@PathParam("name") name: String) = {
    s"Hello, $name, from Scala!";
  }
}
