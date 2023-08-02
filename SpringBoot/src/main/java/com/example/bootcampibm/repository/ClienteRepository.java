package com.example.bootcampibm.repository;

import com.example.bootcampibm.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* Interface que representa o repositório para a entidade "Cliente".
*/

@Repository

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
    
}
