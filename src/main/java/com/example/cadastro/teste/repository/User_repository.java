package com.example.cadastro.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro.teste.model.agent.User_model;

public interface User_repository extends JpaRepository<User_model, Long> {
    Optional<User_model> findByEmail(String email);
   
}
