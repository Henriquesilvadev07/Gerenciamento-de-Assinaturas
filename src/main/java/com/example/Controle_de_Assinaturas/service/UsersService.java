package com.example.Controle_de_Assinaturas.service;

import com.example.Controle_de_Assinaturas.Security.SecurityConfig;
import com.example.Controle_de_Assinaturas.dto.UsersDto;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import com.example.Controle_de_Assinaturas.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

    public UsersModel cadastrar(@Valid UsersDto dto) {
        var user = new UsersModel();
        user.setLogin(dto.login());
        String senhaCriptografada = securityConfig.passwordEncoder().encode(dto.senha());
        user.setSenha(senhaCriptografada);
        return usersRepository.save(user);
    }

    public UsersModel atualizarPorId(Long id, @Valid UsersDto dto){
        var user = usersRepository.findById(id).orElseThrow(()
        -> new EntityNotFoundException("Usuário não encontrado, tente novamente"));
        user.setLogin(dto.login());
        var senhaCripitografada = securityConfig.passwordEncoder().encode(dto.senha());
        return usersRepository.save(user);
    }

}
