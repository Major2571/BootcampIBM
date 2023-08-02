package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.dto.ClienteDto;
import com.example.bootcampibm.repository.ClienteRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço responsável por executar a lógica de negócios relacionada aos clientes.
 */

@Service

public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }
    public Cliente find(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id + Cliente.class.getName()));
    }
}
