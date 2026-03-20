package com.example.Controle_de_Assinaturas.dto;

import com.example.Controle_de_Assinaturas.model.Status;

public record AssinaturasDto(
        String servico,
        Double valor,
        int dataVencimento,
        Status status,
        String plano
) {
}
