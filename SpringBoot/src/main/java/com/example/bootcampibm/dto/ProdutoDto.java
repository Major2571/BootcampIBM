package com.example.bootcampibm.dto;

import java.io.Serializable;

import com.example.bootcampibm.domain.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um objeto de transferência de dados (DTO) para a entidade "Produto".
 */

@NoArgsConstructor
@Setter
@Getter

public class ProdutoDto implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDto(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }

}
