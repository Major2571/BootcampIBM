package com.example.bootcampibm.dto;

import java.io.Serializable;

import com.example.bootcampibm.domain.Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um objeto de transferência de dados (DTO) para a entidade "Cliente".
 */

@NoArgsConstructor
@Setter
@Getter

public class ClienteDto implements Serializable {

    private Integer id;
    private String nome;
    private String email;

    public ClienteDto(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public ClienteDto(int i, String string, String string2) {
    }
    
}
