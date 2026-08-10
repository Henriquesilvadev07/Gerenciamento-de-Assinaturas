package com.example.Controle_de_Assinaturas.repository;

import com.example.Controle_de_Assinaturas.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {

    UserDetails findByLogin(String login);
}
