package co.gywb.demo.web_front.java;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.Set;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

@Path("/albums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumResource {

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

    static {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("co.gywb.demo.web-front.clojure.albums"));
    }
    private static final IFn cljAddAlbum = Clojure.var("co.gywb.demo.web-front.clojure.albums", "add-album");
    private static final IFn cljGetAlbums = Clojure.var("co.gywb.demo.web-front.clojure.albums", "get-albums");

    @GET
    @Path("/v2")
    public String listV2() {
        return (String) cljGetAlbums.invoke();
    }

    @POST
    @Path("/v2")
    public String addV2(String album) {
        cljAddAlbum.invoke(album);
        return "[]";
    }
}
