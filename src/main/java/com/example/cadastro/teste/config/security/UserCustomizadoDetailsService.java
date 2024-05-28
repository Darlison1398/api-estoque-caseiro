package com.example.cadastro.teste.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.cadastro.teste.model.agent.User_model;
import com.example.cadastro.teste.repository.User_repository;

@Component
public class UserCustomizadoDetailsService implements UserDetailsService {

    @Autowired
    private User_repository user_repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_model user_model = this.user_repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Adicionar role "ROLE_USER" para o usuário
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));


        return new org.springframework.security.core.userdetails.User(user_model.getEmail(), user_model.getPassword(), authorities);
    }
}
