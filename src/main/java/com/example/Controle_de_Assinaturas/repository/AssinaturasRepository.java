package com.example.Controle_de_Assinaturas.repository;

import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssinaturasRepository extends JpaRepository<AssinaturasModel, Long> {
    List<AssinaturasModel> findByUsuarioId(Long UsuarioId);
    // No AssinaturasRepository.java
    List<AssinaturasModel> findByUsuario(UsersModel usuario);

}
