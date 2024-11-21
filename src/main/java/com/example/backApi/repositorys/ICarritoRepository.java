package com.example.backApi.repositorys;

import com.example.backApi.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsername(String username);
}
