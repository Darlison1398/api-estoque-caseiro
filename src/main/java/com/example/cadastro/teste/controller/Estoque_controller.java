package com.example.cadastro.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.teste.model.agent.User_model;
import com.example.cadastro.teste.model.estoque.Estoque_model;
import com.example.cadastro.teste.repository.Estoque_repository;
import com.example.cadastro.teste.service.Estoque_service;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:5173")
public class Estoque_controller {

    @Autowired
    private Estoque_service estoque_service;

    @Autowired
    private Estoque_repository estoque_repository;

    @GetMapping
    public List<Estoque_model> todosProdutos(@AuthenticationPrincipal User_model user) {
        /*String email = authentication.getName();
        return estoque_service.AllProdutos(email);*/
        return estoque_repository.findByUser_Id(user.getId());
    }


    @PostMapping("/salvarProduto")
    public Estoque_model salvarProduto(Authentication authentication, @RequestBody Estoque_model produto) {
        String email = authentication.getName();
        return estoque_service.SaveProduto(email, produto);
    }

    @PutMapping("/editarProduto/{id}")
    public Estoque_model editarProduto(@AuthenticationPrincipal User_model user, @PathVariable Long id, @RequestBody Estoque_model newProduto) {
        //String email = authentication.getName();
        //return estoque_service.UpdateProduto(email, id, newProduto);
        return estoque_service.UpdateProduto(user.getEmail(), id, newProduto);
    }

    @DeleteMapping("/deletarProduto/{id}")
    public void apagarProduto(Authentication authentication, @PathVariable long id) {
        String email = authentication.getName();
        estoque_service.DeleteProduto(email, id);
    }

    @GetMapping("/obterProduto/{id}")
    public ResponseEntity<Estoque_model> getProdutoById(@AuthenticationPrincipal User_model user, @PathVariable Long id) {
        Estoque_model produto = estoque_service.getProdutoById(user.getEmail(), id);
        return ResponseEntity.ok(produto);
    }
}
