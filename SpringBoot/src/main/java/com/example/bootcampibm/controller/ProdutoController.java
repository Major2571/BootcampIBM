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

    /**
     * Endpoint para criar um novo produto.
     * 
     * @param produto O objeto Produto a ser criado a partir dos dados fornecidos no
     *                corpo da requisição.
     * @return ResponseEntity com código 201 CREATED e a URI do recurso criado no
     *         header "Location".
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Produto obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para listar todos os produtos.
     * 
     * @return ResponseEntity com código 200 OK e a lista de todos os produtos no
     *         corpo da resposta.
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = service.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    /**
     * Endpoint para obter um produto pelo ID.
     * 
     * @param id O ID do produto a ser obtido.
     * @return ResponseEntity com código 200 OK e o produto encontrado no corpo da
     *         resposta.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = service.find(id);
        return ResponseEntity.ok(produto);
    }

    /**
     * Endpoint para excluir um produto pelo ID.
     * 
     * @param id O ID do produto a ser excluído.
     * @return ResponseEntity com código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para atualizar um produto pelo ID.
     * 
     * @param dto O objeto ProdutoDto contendo os dados atualizados do produto a ser
     *            atualizado.
     * @param id  O ID do produto a ser atualizado.
     * @return ResponseEntity com código 204 NO CONTENT.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ProdutoDto dto, @PathVariable Integer id) {
        Produto obj = service.fromDto(dto);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

}
