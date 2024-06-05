package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {
    //read
    Persona findById(Integer id);
    //create
    Persona create(Persona persona);
    //update
    Persona update(Persona persona);
    //delete
    Boolean delete(Integer id);
    List<Persona> findAll();

}
