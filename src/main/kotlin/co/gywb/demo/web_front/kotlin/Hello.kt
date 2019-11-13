package co.gywb.demo.web_front.kotlin

import co.gywb.demo.web_front.java.Album
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import kotlinx.html.*
import kotlinx.html.dom.*
import kotlinx.html.stream.createHTML

@Path("/hello/kotlin/{name}")
class Hello {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun greet(@PathParam("name") name: String) = "Hello, $name, from Kotlin!"


    fun albumToHtml(album : Album) : String {
        return createHTML().div {
            p { +album.number }
            p { +album.album }
        }
    }
}