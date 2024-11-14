package com.example.backApi.services;

import com.example.backApi.models.Productos;
import com.example.backApi.repositorys.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private IProductoRepository productoRepo;

    @Autowired
    public ProductoService(IProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    //Creamos un producto
    public void crear(Productos producto) {
        productoRepo.save(producto);
    }

    //Obtenemos toda una lista de Productos
    public List<Productos> readAll() {
        return productoRepo.findAll();
    }

    //Obtenemos un producto por su id
    public Optional<Productos> readOne(Long id) {
        return productoRepo.findById(id);
    }

    //Actualizamos un producto
    public void update(Productos producto) {
        productoRepo.save(producto);
    }

    //Eliminamos un producto
    public void delete(Long id) {
        productoRepo.deleteById(id);
    }
}
