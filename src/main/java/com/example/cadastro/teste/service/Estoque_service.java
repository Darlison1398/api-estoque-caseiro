package com.example.cadastro.teste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro.teste.model.agent.User_model;
import com.example.cadastro.teste.model.estoque.Estoque_model;
import com.example.cadastro.teste.repository.Estoque_repository;
import com.example.cadastro.teste.repository.User_repository;

@Service
public class Estoque_service {

    @Autowired
    private Estoque_repository estoque_repository;

    @Autowired
    private User_repository user_repository;


    public List<Estoque_model> AllProdutos(String email) {
        User_model user = user_repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return estoque_repository.findByUser(user);
    }

    public Estoque_model SaveProduto(String email, Estoque_model produto) {
        User_model user = user_repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        produto.setUser(user);
        return estoque_repository.save(produto);
    }

    public Estoque_model UpdateProduto(String email, Long id, Estoque_model newProduto) {
        User_model user = user_repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Estoque_model produto = estoque_repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.getUser().equals(user)) {
            throw new RuntimeException("Você não tem permissão para editar este produto");
        }

        newProduto.setId(produto.getId());
        newProduto.setUser(user);
        return estoque_repository.save(newProduto);
    }

    public void DeleteProduto(String email, Long id) {
        User_model user = user_repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Estoque_model produto = estoque_repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.getUser().equals(user)) {
            throw new RuntimeException("Você não tem permissão para excluir este produto");
        }

        estoque_repository.delete(produto);
    }


}
