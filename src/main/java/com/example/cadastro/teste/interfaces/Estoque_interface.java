package com.example.cadastro.teste.interfaces;


import java.util.List;

import com.example.cadastro.teste.model.estoque.Estoque_model;

public interface Estoque_interface {

    List<Estoque_model> getAllProdutos();
    Estoque_model getProdutoById(Long id);
    Estoque_model getSaveProduto(Estoque_model produto);
    Estoque_model getUpdateProduto(Long id, Estoque_model newProduto);
    Estoque_model getDeleteProduto(Long id);
    
}
