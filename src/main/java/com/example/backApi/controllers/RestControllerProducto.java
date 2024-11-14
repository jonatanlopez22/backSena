package com.example.backApi.controllers;


import com.example.backApi.models.Productos;
import com.example.backApi.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto/")
public class RestControllerProducto {
    private ProductoService productoService;

    @Autowired
    public RestControllerProducto(ProductoService productoService) {
        this.productoService = productoService;
    }

    //Petición para crear un  producto
    @PostMapping(value = "crear", headers = "Accept=application/json")
    public void crearProducto(@RequestBody Productos producto) {
        productoService.crear(producto);
    }

    //Petición para obtener todos los productos en la BD
    @GetMapping(value = "listar", headers = "Accept=application/json")
    public List<Productos> listarProducto() {
        return productoService.readAll();
    }

    //Petición para obtener Producto mediante "ID"
    @GetMapping(value = "listarId/{id}", headers = "Accept=application/json")
    public Optional<Productos> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.readOne(id);
    }

    //Petición para actualizar un Producto
    @PutMapping(value = "actualizar", headers = "Accept=application/json")
    public void actualizarProducto(@RequestBody Productos producto) {
        productoService.update(producto);
    }

    //Petición para eliminar un Producto por "Id"
    @DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.delete(id);
    }
}