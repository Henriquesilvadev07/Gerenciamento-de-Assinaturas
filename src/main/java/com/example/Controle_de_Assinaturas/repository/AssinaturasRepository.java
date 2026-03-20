package com.example.Controle_de_Assinaturas.repository;

import com.example.Controle_de_Assinaturas.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturasRepository extends JpaRepository<Entity, Long> {
}
