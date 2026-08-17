package com.example.Controle_de_Assinaturas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "proximo_vencimento", nullable = true)
    private LocalDate proximoVencimento;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(nullable = false)
    private String plano;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsersModel usuario;

}
