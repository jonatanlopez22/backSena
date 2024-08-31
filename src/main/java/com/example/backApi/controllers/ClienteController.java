package com.example.backApi.controllers;

import com.example.backApi.models.Cliente;
import com.example.backApi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }

    @PostMapping
    public void saveUpdate(@RequestBody Cliente cliente) {
         clienteService.saveOrUpdate(cliente);
    }

    @DeleteMapping("/{clineteId}")
    public void  delete(@PathVariable("clineteId") Integer clieteId) {
        clienteService.delete(clieteId);
    }

    @GetMapping("/{clineteId}")
    public Optional<Cliente> getByID(@PathVariable("clineteId") Integer clieteId) {
       return clienteService.getCliente(clieteId);
    }







}

