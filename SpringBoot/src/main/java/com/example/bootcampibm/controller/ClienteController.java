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

    /**
     * Endpoint para criar um novo cliente.
     * 
     * @param cliente O objeto Cliente a ser criado a partir dos dados fornecidos no
     *                corpo da requisição.
     * @return ResponseEntity com código 201 CREATED e a URI do recurso criado no
     *         header "Location".
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cliente cliente) {
        Cliente obj = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para listar todos os clientes.
     * 
     * @return ResponseEntity com código 200 OK e a lista de todos os clientes no
     *         corpo da resposta.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    /**
     * Endpoint para obter um cliente pelo ID.
     * 
     * @param id O ID do cliente a ser obtido.
     * @return ResponseEntity com código 200 OK e o cliente encontrado no corpo da
     *         resposta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = service.find(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Endpoint para excluir um cliente pelo ID.
     * 
     * @param id O ID do cliente a ser excluído.
     * @return ResponseEntity com código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para atualizar um cliente pelo ID.
     * 
     * @param dto O objeto ClienteDto contendo os dados atualizados do cliente a ser
     *            atualizado.
     * @param id  O ID do cliente a ser atualizado.
     * @return ResponseEntity com código 204 NO CONTENT.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ClienteDto dto, @PathVariable Integer id) {
        Cliente obj = service.fromDto(dto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

}
