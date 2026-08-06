package com.example.Controle_de_Assinaturas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assinaturas")
public class AssinaturasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String servico;

    private Double valor;

    private int dataVencimento;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private String plano;

}
