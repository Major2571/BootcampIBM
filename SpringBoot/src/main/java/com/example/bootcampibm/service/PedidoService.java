package com.example.bootcampibm.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bootcampibm.domain.Pedido;
import com.example.bootcampibm.dto.PedidoDto;
import com.example.bootcampibm.repository.PedidoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;

/**
 * Classe de serviço responsável por executar a lógica de negócios relacionada aos pedidos.
 */

@Service

public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    /**
     * Insere um novo pedido no banco de dados.
     * 
     * @param obj O objeto Pedido a ser inserido.
     * @return O pedido inserido com o ID atribuído e a data de criação definida.
     */
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setData(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj = pedidoRepository.save(obj);
        return obj;
    }

    /**
     * Retorna uma lista de todos os pedidos.
     * 
     * @return A lista de pedidos encontrados.
     */
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    /**
     * Busca um pedido pelo ID.
     * 
     * @param id O ID do pedido a ser buscado.
     * @return O pedido encontrado.
     * @throws ObjectNotFoundException se o pedido não for encontrado no banco de
     *                                 dados.
     */
    public Pedido find(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Objeto nao encontrado! Id:" + id + Pedido.class.getName()));
    }

    /**
     * Exclui um pedido pelo ID.
     * 
     * @param id O ID do pedido a ser excluído.
     */
    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um pedido existente.
     * 
     * @param obj O objeto Pedido contendo os dados atualizados.
     * @return O pedido atualizado.
     * @throws ObjectNotFoundException se o pedido não for encontrado no banco de
     *                                 dados.
     */
    public Pedido update(Pedido obj) {
        Pedido attPedido = find(obj.getId());
        updateData(attPedido, obj);
        return pedidoRepository.save(attPedido);
    }

    private void updateData(Pedido novo, Pedido antigo) {
        novo.setId(antigo.getId());
        novo.setData(antigo.getData());
    }

    /**
     * Converte um objeto PedidoDto para um objeto Pedido.
     * 
     * @param objDto O objeto PedidoDto a ser convertido.
     * @return O pedido convertido.
     */
    public Pedido fromDto(PedidoDto objDto) {
        return new Pedido(objDto.getId(), objDto.getData(), objDto.getCliente());
    }

}
