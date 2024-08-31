package com.example.backApi.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcliete;
    @Column(unique = true,nullable = false)
    private String dni;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String telefono;
    private String direccion;

}
