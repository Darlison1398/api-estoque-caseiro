package com.example.cadastro.teste.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.teste.config.security.TokenService;
import com.example.cadastro.teste.model.agent.User_model;
import com.example.cadastro.teste.model.dto.LoginResqestDTO;
import com.example.cadastro.teste.model.dto.RegisterRequestDTO;
import com.example.cadastro.teste.model.dto.ResponseDTO;
import com.example.cadastro.teste.repository.User_repository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final User_repository user_repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity fazerLogin(@RequestBody LoginResqestDTO body) {

        User_model user_model = this.user_repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado aqui no controller."));

        if (passwordEncoder.matches(body.password(), user_model.getPassword())){

            String token = this.tokenService.createToken(user_model);

            return ResponseEntity.ok(new ResponseDTO(user_model.getNome(), token));
        }

        return ResponseEntity.badRequest().build();

    }


    @PostMapping("/criarConta")
    public ResponseEntity criarConta(@RequestBody RegisterRequestDTO body) {

        Optional<User_model> user_model = this.user_repository.findByEmail(body.email());

        if (user_model.isEmpty()) {

            User_model newUser = new User_model();
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            newUser.setLastname(body.lastname());
            newUser.setPassword(passwordEncoder.encode(body.password()));

            this.user_repository.save(newUser);

            String token = this.tokenService.createToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
            
        }

        return ResponseEntity.badRequest().build();
    }

    
    
}
