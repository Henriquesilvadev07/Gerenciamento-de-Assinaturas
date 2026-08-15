package com.example.Controle_de_Assinaturas.dto;

import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.model.StatusEnum;

public record AssinaturasResponseDto(
        Long id,
        String servico,
        Double valor,
        int dataVencimento,
        StatusEnum status,
        String plano,
        Long usuarioId // Se quiser mostrar de quem é a assinatura
) {
    // Construtor auxiliar para converter da Model para o DTO facilmente
    public AssinaturasResponseDto(AssinaturasModel assinatura) {
        this(
                assinatura.getId(),
                assinatura.getServico(),
                assinatura.getValor(),
                assinatura.getDataVencimento(),
                assinatura.getStatus(),
                assinatura.getPlano(),
                assinatura.getUsuario() != null ? assinatura.getUsuario().getId() : null
        );
    }
}
