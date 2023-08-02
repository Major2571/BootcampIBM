package com.example.bootcampibm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.dto.ProdutoDto;
import com.example.bootcampibm.repository.ProdutoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;

/**
 * Classe de serviço responsável por executar a lógica de negócios relacionada aos produtos.
 */

@Service

public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para inserir um novo produto.
     * 
     * @param obj O objeto Produto a ser inserido.
     * @return O produto inserido com o ID gerado.
     */
    public Produto insert(Produto obj) {
        obj.setId(null);
        obj = produtoRepository.save(obj);
        return obj;
    }

    /**
     * Método para listar todos os produtos.
     * 
     * @return A lista de todos os produtos.
     */
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    /**
     * Método para buscar um produto pelo ID.
     * 
     * @param id O ID do produto a ser buscado.
     * @return O produto encontrado com o ID especificado.
     * @throws ObjectNotFoundException Se o produto não for encontrado.
     */
    public Produto find(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Produto nao encontrado! Id:" + id + Produto.class.getName()));
    }

    /**
     * Método para excluir um produto pelo ID.
     * 
     * @param id O ID do produto a ser excluído.
     */
    public void delete(Integer id) {
        produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar um produto existente pelo ID.
     * 
     * @param obj O objeto Produto com os dados atualizados do produto.
     * @return O produto atualizado.
     * @throws ObjectNotFoundException Se o produto não for encontrado.
     */
    public Produto update(Produto obj) {
        Produto attProduto = find(obj.getId());
        updateData(attProduto, obj);
        return produtoRepository.save(attProduto);
    }

    /**
     * Método auxiliar para atualizar os dados de um produto.
     * 
     * @param novo   O objeto Produto a ser atualizado.
     * @param antigo O objeto Produto com os dados atualizados do produto.
     */
    private void updateData(Produto novo, Produto antigo) {
        novo.setNome(antigo.getNome());
        novo.setPreco(antigo.getPreco());
    }

    /**
     * Método para converter um objeto ProdutoDto em um objeto Produto.
     * 
     * @param objDto O objeto ProdutoDto a ser convertido.
     * @return O objeto Produto convertido.
     */
    public Produto fromDto(ProdutoDto objDto) {
        return new Produto(objDto.getId(), objDto.getNome(), objDto.getPreco());
    }

}
