package com.example.bootcampibm.repository;

import com.example.bootcampibm.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* Interface que representa o repositório para a entidade "Pedido".
*/

@Repository

public interface PedidoRepository extends JpaRepository <Pedido, Integer> {

}
