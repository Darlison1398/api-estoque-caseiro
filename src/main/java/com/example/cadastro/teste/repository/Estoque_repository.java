package com.example.cadastro.teste.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro.teste.model.agent.User_model;
import com.example.cadastro.teste.model.estoque.Estoque_model;

public interface Estoque_repository extends JpaRepository<Estoque_model, Long>{
    //List<Estoque_model> findByUser(User_model userId);
    //Optional<Estoque_model> findByIdAndUser(Long id, User_model user);
    List<Estoque_model> findByUser_Id(Long userId);
}
