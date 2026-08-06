package com.example.Controle_de_Assinaturas.dto;

import com.example.Controle_de_Assinaturas.model.StatusEnum;

public record AssinaturasDto(
        String servico,
        Double valor,
        int dataVencimento,
        StatusEnum statusEnum,
        String plano
) {
}
