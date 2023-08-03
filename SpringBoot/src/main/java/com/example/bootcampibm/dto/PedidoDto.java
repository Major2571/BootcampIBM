package com.example.bootcampibm.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Pedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um objeto de transferência de dados (DTO) para a entidade "Pedido".
 */

@NoArgsConstructor
@Setter
@Getter

public class PedidoDto implements Serializable {

    private Integer id;
    private Date data;
    private Cliente cliente;

    public PedidoDto(Pedido obj){
        id = obj.getId();
        data = obj.getData();
        cliente = obj.getCliente();
    }
    
}
