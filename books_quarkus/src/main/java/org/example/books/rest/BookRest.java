package org.example.books.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.books.clients.AuthorRestClient;
import org.example.books.db.Book;
import org.example.books.dtos.BookDto;
import org.example.books.repo.BooksRepository;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {

    @Inject
    BooksRepository repo;
    @Inject
    @RestClient
    AuthorRestClient authorRest;

    @GET
    public List<BookDto> findAll(){

        var books= repo.listAll();
        return books.stream().map(obj->{
            //conectarse al servicio autor
            // http://localhost:9090/authors/{id}
            var author=authorRest.findById(obj.getAuthorId());

            BookDto dto= new BookDto();
            dto.setId(obj.getId());
            dto.setAuthorName(author.getFirstName());
            return dto;
        }).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
        var op=repo.findByIdOptional(id);
        if (op.isEmpty()){
            //No encontrado
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Book book){
        book.setId(null);
        repo.persist(book);
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Book book){
        var obj=repo.findById(id);

        obj.setIsbn(book.getIsbn());
        obj.setTitle(book.getTitle());
        obj.setPrice(book.getPrice());
        obj.setAuthorId(book.getAuthorId());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        repo.deleteById(id);
        return Response.ok().build();

    }



}
