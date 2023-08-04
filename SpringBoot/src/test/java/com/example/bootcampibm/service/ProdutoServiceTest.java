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

import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.repository.ProdutoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testInsertProduto(){
        Produto produto = new Produto(1, "Monitor", 900.7);   

        Mockito.when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto produtoInserido = produtoService.insert(produto);

        assertNotNull(produtoInserido);
        assertEquals(produto.getNome(), produtoInserido.getNome());
        assertEquals(produto.getPreco(), produtoInserido.getPreco());
        assertNull(produtoInserido.getId());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    public void testFindProdutoByIdExistente(){
        Integer produtoId = 1;
        Produto produto = new Produto(produtoId, "Teclado", 250.7);

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        Produto ProdutoEncontrado = produtoService.find(produtoId);

        assertNotNull(ProdutoEncontrado);
        assertEquals(produto.getId(), ProdutoEncontrado.getId());
        assertEquals(produto.getNome(), ProdutoEncontrado.getNome());
        assertEquals(produto.getPreco(), ProdutoEncontrado.getPreco());

        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    public void testFindProdutoByIdNaoExistente(){
        Integer produtoId = 1;

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> produtoService.find(produtoId));
        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    public void testDeleteProduto(){
        Integer produtoId = 1;
        produtoService.delete(produtoId);
        verify(produtoRepository, times(1)).deleteById(produtoId);
    }

    @Test
    public void testUdateProduto(){
        Integer produtoId = 1;
        Produto produtoAtualizado = new Produto(produtoId, "Mouse", 120.3);
        Produto produtoExistente = new Produto(produtoId, "SSD", 205.6);

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        Produto resultado = produtoService.update(produtoAtualizado);

        assertNotNull(resultado);
        assertEquals(produtoId, resultado.getId());
        assertEquals(produtoAtualizado.getNome(), resultado.getNome());
        assertEquals(produtoAtualizado.getPreco(), resultado.getPreco());

        verify(produtoRepository, times(1)).findById(produtoId);
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

}