package com.example.bootcampibm.repository;

import com.example.bootcampibm.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* Interface que representa o repositório para a entidade "Produto".
*/

@Repository

public interface ProdutoRepository extends JpaRepository <Produto, Integer> {
    
}
