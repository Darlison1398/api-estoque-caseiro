package com.example.cadastro.teste.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.cadastro.teste.model.agent.User_model;

@Service
public class TokenService {

    
    @Value("${api.security.token.secret}")
    private String secret;
    

    public String createToken(User_model user_model) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                            .withIssuer("login-auth-api")
                            .withSubject(user_model.getEmail())
                            .withExpiresAt(this.gerarDataHoraExpiracao())
                            .sign(algorithm);

            
            return token;

        } catch (JWTCreationException e) {
            
            throw new RuntimeException("Erro ao tentar fazer autenticação");
        }

    }

    //validando o token
    public String validarToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                        .build()
                        .verify(token)
                        .getSubject();

         

        } catch (JWTVerificationException e) {
            return null;    
        }
    }

    public Instant gerarDataHoraExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    
}
