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

    /**
     * Insere um novo cliente no banco de dados.
     * 
     * @param obj O objeto Cliente a ser inserido.
     * @return O cliente inserido com o ID atribuído.
     */
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }

    /**
     * Retorna uma lista de todos os clientes.
     * 
     * @return A lista de clientes encontrados.
     */
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    /**
     * Busca um cliente pelo ID.
     * 
     * @param id O ID do cliente a ser buscado.
     * @return O cliente encontrado.
     * @throws ObjectNotFoundException se o cliente não for encontrado no banco de
     *                                 dados.
     */
    public Cliente find(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id + Cliente.class.getName()));
    }

    /**
     * Exclui um cliente pelo ID.
     * 
     * @param id O ID do cliente a ser excluído.
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Atualiza os dados de um cliente existente.
     * 
     * @param obj O objeto Cliente contendo os dados atualizados.
     * @return O cliente atualizado.
     * @throws ObjectNotFoundException se o cliente não for encontrado no banco de
     *                                 dados.
     */
    public Cliente update(Cliente obj) {
        Cliente attCliente = find(obj.getId());
        updateData(attCliente, obj);
        return repository.save(attCliente);
    }

    private void updateData(Cliente novo, Cliente antigo) {
        novo.setNome(antigo.getNome());
        novo.setEmail(antigo.getEmail());
    }

    /**
     * Converte um objeto ClienteDto para um objeto Cliente.
     * 
     * @param objDto O objeto ClienteDto a ser convertido.
     * @return O cliente convertido.
     */
    public Cliente fromDto(ClienteDto objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail());
    }
}
