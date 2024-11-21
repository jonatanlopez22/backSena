package com.example.backApi.services;

import com.example.backApi.models.Carrito;
import com.example.backApi.repositorys.ICarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {
    private ICarritoRepository carritoRepo;

    @Autowired
    public CarritoService(ICarritoRepository carrtoRepo) {
        this.carritoRepo = carrtoRepo;
    }

    //Creamos un carrito
    public void crear(Carrito carrito) {
        carritoRepo.save(carrito);
    }

    //Obtenemos toda una lista de carritos
    public List<Carrito> readAll() {
        return carritoRepo.findAll();
    }

    //Obtenemos un carrito por su id
    public Optional<Carrito> readOne(Long id) {
        return carritoRepo.findById(id);
    }

    //Actualizamos un carrito
    public void update(Carrito producto) {
        carritoRepo.save(producto);
    }

    //Eliminamos un carrito
    public void delete(Long id) {
        carritoRepo.deleteById(id);
    }

    public List<Carrito> findByUsername(String username) {
        System.out.println("aqui------------------------------------2222");
        return carritoRepo.findByUsername(username); }
}
