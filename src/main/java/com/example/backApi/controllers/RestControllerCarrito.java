package com.example.backApi.controllers;

import com.example.backApi.models.Carrito;
import com.example.backApi.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito/")

public class RestControllerCarrito {
    private CarritoService carritoService;

    @Autowired
    public RestControllerCarrito(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    //Petición para crear un  Carrito
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public void crearCarrito(@RequestBody Carrito carrito) {
        carritoService.crear(carrito);
    }

    //Petición para obtener todos los productos en la BD
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<Carrito> listarCarrito() {return carritoService.readAll();}

    //Petición para obtener Producto mediante "ID"
    @GetMapping(value = "listarId/{id}", headers = "Accept=application/json")
    public Optional<Carrito> obtenerCarritoPorId(@PathVariable Long id) {
        return carritoService.readOne(id);
    }

    //Petición para obtener Producto mediante "User"
    @GetMapping(value = "listarUser/{username}", headers = "Accept=application/json")
    public List<Carrito> obtenerCarritoPorUsername(
            @PathVariable String username) {
        return carritoService.findByUsername(username); }


    //Petición para actualizar un Producto
    @PutMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarCarrito(@RequestBody Carrito producto) {
        carritoService.update(producto);
    }

    //Petición para eliminar un Producto por "Id"
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarCarrito(@PathVariable Long id) {
        carritoService.delete(id);
    }


}
