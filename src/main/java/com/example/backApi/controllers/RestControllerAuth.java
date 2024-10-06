package com.example.backApi.controllers;



import com.example.backApi.dtos.DtoAuthRespuesta;
import com.example.backApi.dtos.DtoLogin;
import com.example.backApi.dtos.DtoRegistro;
import com.example.backApi.dtos.DtoRegistroRespuesta;
import com.example.backApi.models.Roles;
import com.example.backApi.models.Usuarios;
import com.example.backApi.repositorys.IRolesRepository;
import com.example.backApi.repositorys.IUsuariosRepository;
import com.example.backApi.security.JwtGenerador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth/")
public class RestControllerAuth {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRolesRepository rolesRepository;
    private IUsuariosRepository usuariosRepository;
    private JwtGenerador jwtGenerador;

    @Autowired

    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRolesRepository rolesRepository, IUsuariosRepository usuariosRepository, JwtGenerador jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerador = jwtGenerador;
    }
    //Método para poder registrar usuarios con role "user"
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("register")
    public ResponseEntity<DtoRegistroRespuesta> registrar(@RequestBody DtoRegistro dtoRegistro) {
        System.out.println("dto aqui---------------------------"+dtoRegistro);
        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
            return new ResponseEntity<>(new DtoRegistroRespuesta(false), HttpStatus.BAD_REQUEST);
        }

        Usuarios usuarios = new Usuarios();
        usuarios.setUsername(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        usuarios.setFirstName(dtoRegistro.getFirstName());
        usuarios.setLastName(dtoRegistro.getLastName());
        usuarios.setPhone(dtoRegistro.getUsername());
        usuarios.setAddress(dtoRegistro.getAddress());
        usuarios.setEmail(dtoRegistro.getEmail());
        Roles roles = rolesRepository.findByName("USER").get();
        System.out.println("paso");
        usuarios.setRoles(Collections.singletonList(roles));
        System.out.println("paso2");
        usuariosRepository.save(usuarios);

        return new ResponseEntity<>(new DtoRegistroRespuesta(true), HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo ADMIN
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuarios usuarios = new Usuarios();
        usuarios.setUsername(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Roles roles = rolesRepository.findByName("ADMIN").get();
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de admin exitoso", HttpStatus.OK);
    }

    //Método para poder logear un usuario y obtener un token
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }


}