package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static SeContainer container;
    static List<Persona> listarPersonas(Request req, Response res){

        res.type("application/json");

        var servicio = container.select(ServicioPersona.class).get();

        return servicio.findAll();
    }

    static Persona buscarPersona(Request req, Response res){

        res.type("application/json");
        String _id = req.params(":id");

        var servicio = container.select(ServicioPersona.class).get();

        var persona = servicio.findById(Integer.valueOf(_id));

        if(persona==null){
            halt(404,"Persona no encontrada");
        }

        return persona;
    }
    static Persona crearPersona(Request req, Response res){
        res.type("application/json");
    System.out.println(req.params());
        var persona = new Persona();
        persona.setNombre(req.queryParams("nombre"));
        persona.setDireccion(req.queryParams("direccion"));
        persona.setEdad(Integer.valueOf(req.queryParams("edad")));
        var servicio = container.select(ServicioPersona.class).get();

        return servicio.create(persona);
    }

    static Persona actualizarPersona(Request req, Response res){
        res.type("application/json");

        var persona = new Persona();
        persona.setId(Integer.valueOf(req.params(":id")));
        persona.setNombre(req.queryParams("nombre"));
        persona.setDireccion(req.queryParams("direccion"));
        persona.setEdad(Integer.valueOf(req.queryParams("edad")));

        var servicio = container.select(ServicioPersona.class).get();

        return servicio.update(persona);
    }

    static Boolean borrarPersona(Request req, Response res){
        res.type("application/json");

        var persona = new Persona();
        persona.setId(Integer.valueOf(req.params(":id")));

        var servicio = container.select(ServicioPersona.class).get();

        return servicio.delete(persona.getId());
    }

    public static void main(String[] args) {
         container= SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioPersona servicio = container
                .select(ServicioPersona.class)
                .get();
        servicio.create(new Persona(1,"Juan", "Quito", 20));
        servicio.create(new Persona(2,"Juan", "Guayaqiol", 23));
        servicio.create(new Persona(3,"Juan", "Cuenca", 21));

        servicio.update(new Persona(1,"Juan", "Quito", 20));

        servicio.delete(2);

        servicio
                .findAll()
                .stream()
                .map(Persona::getNombre)
                .forEach(System.out::println);


        port(8080);
        Gson gson = new Gson();

        get("/personas", Main::listarPersonas,gson::toJson);
        get("/personas/:id", Main::buscarPersona, gson::toJson);
        post("/personas", Main::crearPersona, gson::toJson);
        put("/personas/:id", Main::actualizarPersona, gson::toJson);
        delete("/personas/:id", Main::borrarPersona, gson::toJson);

    }
}
