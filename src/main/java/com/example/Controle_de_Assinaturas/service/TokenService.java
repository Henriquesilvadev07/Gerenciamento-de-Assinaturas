package com.example.Controle_de_Assinaturas.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(UsersModel user) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("RickDev")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);

        } catch (RuntimeException exception) {
            throw new RuntimeException("Falha ao Criar token JWT");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("RickDev")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (RuntimeException exception) {
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
