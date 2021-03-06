package co.gywb.demo.web_front.kotlin

import co.gywb.demo.web_front.java.Album
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/albums")
@Produces(MediaType.TEXT_HTML)
class AlbumResource {

    @GET
    @Path("/list.html")
    fun renderAlbums(): String {
        return AlbumsHTMLRenderer.all()
    }

}

object AlbumsHTMLRenderer {
    val albums = mutableSetOf<Album>()

    @JvmStatic
    fun add(album: Album) = albums.add(album)

    @JvmStatic
    fun getAll() = albums

    @JvmStatic
    fun all() = createHTML().html {
        head {
            link(rel = "stylesheet", href = "/bootstrap.min.css")
        }
        body {
            div(classes = "container") {
                table(classes = "table table-bordered table-striped table-hover") {
                    thead {
                        tr {
                            arrayOf("Number", "Year",
                                    "Album", "Artist",
                                    "Genre", "Subgenre")
                                    .map { th(classes = "outline") { +it } }
                        }
                    }
                    tbody {
                        albums.map {
                            tr {
                                td { b { +it.number } }
                                td { +it.year }
                                td { +it.album }
                                td { +it.artist }
                                td { +it.genre }
                                td { +it.subgenre }
                            }
                        }
                    }
                }
            }
        }
    }
}
