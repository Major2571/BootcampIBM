package com.example.bootcampibm.controller;

import java.net.URI;
import java.util.List;

import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.dto.ProdutoDto;
import com.example.bootcampibm.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controlador que gerencia as requisições relacionadas aos produtos.
 */

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Produto obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = service.find(id);
        return ResponseEntity.ok(produto);
    }
}
