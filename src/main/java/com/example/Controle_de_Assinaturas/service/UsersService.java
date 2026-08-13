package com.example.Controle_de_Assinaturas.service;

import com.example.Controle_de_Assinaturas.Security.SecurityConfig;
import com.example.Controle_de_Assinaturas.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = usersRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return user;
    }
}
