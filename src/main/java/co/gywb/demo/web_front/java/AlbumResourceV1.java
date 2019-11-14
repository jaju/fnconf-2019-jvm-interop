package co.gywb.demo.web_front.java;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.Set;

@Path("/albums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumResourceV1 {

    private Set<Album> albums = new HashSet<>();

    @GET
    @Path("/v1")
    public Set<Album> list() {
        return albums;
    }

    @POST
    @Path("/v1")
    public Set<Album> add(Album album) {
        albums.add(album);
        return albums;
    }

}
