package com.example.Controle_de_Assinaturas.dto;

import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.model.StatusEnum;

import java.time.LocalDate;

public record AssinaturasResponseDto(
        Long id,
        String servico,
        Double valor,
        int dataVencimento,
        LocalDate proximoVencimento, // <--- Adicione este campo
        StatusEnum status,
        String plano
) {
    public AssinaturasResponseDto(AssinaturasModel assinatura) {
        this(
                assinatura.getId(),
                assinatura.getServico(),
                assinatura.getValor(),
                assinatura.getDataVencimento(),
                assinatura.getProximoVencimento(), // <--- Mapeie aqui
                assinatura.getStatus(),
                assinatura.getPlano()
        );
    }
}

