package com.example.Controle_de_Assinaturas.repository;

import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturasRepository extends JpaRepository<AssinaturasModel, Long> {
}
