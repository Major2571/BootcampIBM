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

@WebMvcTest(ClienteControllerTest.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClienteControllerTest clienteController;

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

    @Test
    public void testDeleteCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1")).andExpect(status().isNoContent());
    }

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
