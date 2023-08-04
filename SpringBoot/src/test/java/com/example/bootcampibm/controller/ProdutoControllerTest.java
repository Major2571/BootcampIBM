package com.example.bootcampibm.controller;

import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.service.ProdutoService;
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

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoControllerTest produtoController;

    @Test
    public void testInsertProduto() throws Exception {
        Produto produtoInserido = new Produto(1, "Monitor", 800.6);

        when(produtoService.insert(any(Produto.class))).thenReturn(produtoInserido);

        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":1,\"nome\":\"Monitor\",\"preco\":\"800.6\"}"))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/produtos/1"));
    }

    @Test
    public void testDeleteProduto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/produtos/1")).andExpect(status().isNoContent());
    }

    @Test
    public void testFindProdutoByIdExistente() throws Exception {
        Produto produtoEncontrado = new Produto(1, "Monitor", 800.6);

        when(produtoService.find(1)).thenReturn(produtoEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/produtos/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Monitor"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.preco").value(800.6));
        
    }

}
