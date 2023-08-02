package com.example.bootcampibm.controller;

import java.net.URI;
import java.util.List;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.dto.ClienteDto;
import com.example.bootcampibm.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controlador que gerencia as requisições relacionadas aos clientes.
 */

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    private ClienteService service;
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cliente cliente) {
        Cliente obj = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = service.find(id);
        return ResponseEntity.ok(cliente);
    }

}
