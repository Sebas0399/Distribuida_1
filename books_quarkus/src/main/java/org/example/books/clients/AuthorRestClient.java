package org.example.books.clients;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.books.dtos.AuthorDto;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@RegisterRestClient(baseUri="http://localhost:9091")
public interface AuthorRestClient {
   // @GET
    //public List<Object> findAll() ;

    @GET
    @Path("/{id}")
    public AuthorDto findById(@PathParam("id") Integer id) ;

}
