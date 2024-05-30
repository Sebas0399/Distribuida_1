package com.distribuida;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Principal {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("pu-distribuida");
        EntityManager em=emf.createEntityManager();
        var p=new Persona();
        p.setNombre("Atnony");
        p.setDireccion("Casa");
        p.setId(1);
        p.setEdad(24);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        //listar
        TypedQuery<Persona>query= em.createQuery("select o from Persona o order by id asc",Persona.class);
        query.getResultStream().map(Persona::getNombre).forEach(System.out::println);
        em.close();
        emf.close();
    }
}
