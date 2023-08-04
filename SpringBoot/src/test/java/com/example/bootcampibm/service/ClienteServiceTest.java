package com.example.bootcampibm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.repository.ClienteRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;

/**
 * Classe de teste para o serviço ClienteService.
 * Utiliza Mockito para simular o comportamento do repositório.
 */
@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    /**
     * Testa o método insert do ClienteService.
     * Verifica se o cliente foi inserido corretamente e se os atributos estão
     * corretos.
     */
    @Test
    public void testInsertCliente() {

        Cliente cliente = new Cliente(1, "teste", "teste@email");

        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente clienteInserido = clienteService.insert(cliente);

        assertNotNull(clienteInserido);
        assertEquals(cliente.getNome(), clienteInserido.getNome());
        assertEquals(cliente.getEmail(), clienteInserido.getEmail());
        assertNull(clienteInserido.getId());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    /**
     * Testa o método find do ClienteService quando o cliente com o ID existe.
     * Verifica se o cliente foi encontrado corretamente e se os atributos estão
     * corretos.
     */
    @Test
    public void testFindClienteByIdExistente() {
        Integer clienteId = 1;
        Cliente cliente = new Cliente(clienteId, "Jose", "jose@email.com");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.find(clienteId);

        assertNotNull(clienteEncontrado);
        assertEquals(cliente.getId(), clienteEncontrado.getId());
        assertEquals(cliente.getNome(), clienteEncontrado.getNome());
        assertEquals(cliente.getEmail(), clienteEncontrado.getEmail());

        verify(clienteRepository, times(1)).findById(clienteId);
    }

    /**
     * Testa o método find do ClienteService quando o cliente com o ID não existe.
     * Verifica se é lançada uma exceção ObjectNotFoundException.
     */
    @Test
    public void testFindClienteByIdNaoExistente() {
        Integer clienteId = 1;

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> clienteService.find(clienteId));
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    /**
     * Testa o método delete do ClienteService.
     * Verifica se o método clienteRepository.deleteById foi chamado corretamente.
     */
    @Test
    public void testDeleteCliente() {
        Integer clienteId = 1;
        clienteService.delete(clienteId);
        verify(clienteRepository, times(1)).deleteById(clienteId);
    }

    /**
     * Testa o método update do ClienteService.
     * Verifica se o cliente foi atualizado corretamente e se os atributos estão
     * corretos.
     */
    @Test
    public void testUdateCliente() {
        Integer clienteId = 1;
        Cliente clienteAtualizado = new Cliente(clienteId, "Jose", "jose@email.com");
        Cliente clienteExistente = new Cliente(clienteId, "Rafa", "rafa@email.com");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.update(clienteAtualizado);

        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getId());
        assertEquals(clienteAtualizado.getNome(), resultado.getNome());
        assertEquals(clienteAtualizado.getEmail(), resultado.getEmail());

        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

}