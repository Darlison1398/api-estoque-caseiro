package com.example.cadastro.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.teste.model.estoque.Estoque_model;
import com.example.cadastro.teste.service.Estoque_service;

@RestController
@RequestMapping("/produtos")
public class Estoque_controller {

    @Autowired
    private Estoque_service estoque_service;

    @GetMapping
    public List<Estoque_model> todosProdutos(Authentication authentication) {
        String email = authentication.getName();
        return estoque_service.AllProdutos(email);
    }

    @PostMapping("/salvarProduto")
    public Estoque_model salvarProduto(Authentication authentication, @RequestBody Estoque_model produto) {
        String email = authentication.getName();
        return estoque_service.SaveProduto(email, produto);
    }

    @PutMapping("/editarProduto/{id}")
    public Estoque_model editarProduto(Authentication authentication, @PathVariable Long id, @RequestBody Estoque_model newProduto) {
        String email = authentication.getName();
        return estoque_service.UpdateProduto(email, id, newProduto);
    }

    @DeleteMapping("/deletarProduto/{id}")
    public void apagarProduto(Authentication authentication, @PathVariable long id) {
        String email = authentication.getName();
        estoque_service.DeleteProduto(email, id);
    }
}
