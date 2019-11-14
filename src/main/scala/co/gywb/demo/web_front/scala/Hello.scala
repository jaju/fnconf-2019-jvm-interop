package co.gywb.demo.web_front.scala

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, PathParam, Produces};

@Path("/hello/scala")
class Hello {
  @GET
  @Path("/{name}")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def greet(@PathParam("name") name: String) = {
    s"Hello, $name, from Scala!";
  }
}
