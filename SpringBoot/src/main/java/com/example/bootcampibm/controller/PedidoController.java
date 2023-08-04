package com.example.bootcampibm.controller;

import java.net.URI;
import java.util.List;

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

import com.example.bootcampibm.domain.Pedido;
import com.example.bootcampibm.dto.PedidoDto;
import com.example.bootcampibm.service.PedidoService;

@RestController
@RequestMapping("/pedidos")

public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Endpoint para criar um novo pedido.
     * 
     * @param obj O objeto Pedido a ser criado a partir dos dados fornecidos no
     *            corpo da requisição.
     * @return ResponseEntity com código 201 CREATED e a URI do recurso criado no
     *         header "Location".
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Pedido obj) {
        obj = pedidoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para listar todos os pedidos.
     * 
     * @return ResponseEntity com código 200 OK e a lista de todos os pedidos no corpo da resposta.
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Endpoint para obter um pedido pelo ID.
     * 
     * @param id O ID do pedido a ser obtido.
     * @return ResponseEntity com código 200 OK e o pedido encontrado no corpo da resposta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        Pedido pedido = pedidoService.find(id);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Endpoint para excluir um pedido pelo ID.
     * 
     * @param id O ID do pedido a ser excluído.
     * @return ResponseEntity com código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para atualizar um pedido pelo ID.
     * 
     * @param dto O objeto PedidoDto contendo os dados atualizados do pedido a ser
     *            atualizado.
     * @param id  O ID do pedido a ser atualizado.
     * @return ResponseEntity com código 204 NO CONTENT.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody PedidoDto dto, @PathVariable Integer id) {
        Pedido obj = pedidoService.fromDto(dto);
        obj.setId(id);
        return ResponseEntity.noContent().build();
    }
}