package com.distribuida.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="persona")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    private Integer id;

    private String nombre;
    private String direccion;
    private Integer edad;

}
