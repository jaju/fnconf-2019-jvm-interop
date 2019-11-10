package co.gywb.demo.web_front

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello/kotlin/{name}")
class HelloKotlin {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun greet(@PathParam("name") name: String) = "Hello, $name, from Kotlin!"
}
