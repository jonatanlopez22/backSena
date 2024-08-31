package com.example.backApi.services;

import com.example.backApi.models.Cliente;
import com.example.backApi.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Integer id) {
        return clienteRepository.findById(id);
    }

    public void  saveOrUpdate(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

}
