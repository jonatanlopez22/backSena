package com.example.backApi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "username",unique = true,nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    //Usamos fetchType en EAGER para que cada vez que se acceda o se extraiga un usuario de la BD, este se traiga todos sus roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /*Con JoinTable estaremos creando una tabla que unirá la tabla de usuario y role, con lo cual tendremos un total de 3 tablas
    relacionadas en la tabla "usuarios_roles", a través de sus columnas usuario_id que apuntara al ID de la tabla usuario
    y role_id que apuntara al Id de la tabla role */
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
            ,inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
    private List<Roles> roles = new ArrayList<>();
}
