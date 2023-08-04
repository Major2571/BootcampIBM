package com.example.bootcampibm.controller;


import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Este arquivo é uma classe de testes para o controlador 'ClienteController' no pacote 'com.example.bootcampibm.controller'.
 *
 * A anotação '@WebMvcTest(ClienteController.class)' indica que este teste é um teste de integração para o controlador 'ClienteController'.
 * Ele permite testar o comportamento do controlador sem carregar toda a aplicação Spring, focando apenas no comportamento da camada de controle.
 *
 * Neste teste, são utilizados os recursos do Mockito e do Spring Test para simular as respostas do serviço e realizar as chamadas aos endpoints do controlador.
 */

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteControllerTest clienteController;

    /**
     * Teste para a operação de inserção de cliente.
     * O método simula uma requisição HTTP POST para o endpoint '/clientes' com um objeto JSON representando o cliente a ser inserido.
     * O método espera que o status retornado seja '201 Created' e que o cabeçalho da resposta contenha o local onde o novo cliente foi criado.
     */
    @Test
    public void testInsertCliente() throws Exception {
        Cliente clienteInserido = new Cliente(1, "maria", "maria@email.com");

        when(clienteService.insert(any(Cliente.class))).thenReturn(clienteInserido);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":1,\"nome\":\"maria\",\"email\":\"maria@email.com\"}"))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/clientes/1"));
    }

    /**
     * Teste para a operação de exclusão de cliente.
     * O método simula uma requisição HTTP DELETE para o endpoint '/clientes/1', onde '1' é o ID do cliente a ser excluído.
     * O método espera que o status retornado seja '204 No Content', indicando que a operação foi bem-sucedida e não há conteúdo para ser retornado.
     */
    @Test
    public void testDeleteCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1")).andExpect(status().isNoContent());
    }

    /**
     * Teste para a operação de busca de cliente por ID.
     * O método simula uma requisição HTTP GET para o endpoint '/clientes/1', onde '1' é o ID do cliente a ser buscado.
     * O método espera que o status retornado seja '200 OK' e que o corpo da resposta contenha os dados do cliente encontrado, como o ID, nome e email.
     */
    @Test
    public void testFindClienteByIdExistente() throws Exception {
        Cliente clienteEncontrado = new Cliente(1, "maria", "maria@email.com");

        when(clienteService.find(1)).thenReturn(clienteEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("maria"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("maria@email.com"));
        
    }

}
