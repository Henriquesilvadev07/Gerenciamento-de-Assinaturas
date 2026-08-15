package com.example.Controle_de_Assinaturas.dto;

import com.example.Controle_de_Assinaturas.model.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssinaturasDto(
        @NotBlank(message = "É necessário colocar o serviço") String servico,
        @NotNull(message = "É necessário colocar o valor") Double valor,
        @NotNull(message = "É necessário colocar a data de vencimento") int dataVencimento,
        @NotNull(message = "É necessário colocar o status") StatusEnum status,
        @NotBlank(message = "É necessário colocar qual o plano") String plano,
        @NotNull(message = "É necessario colocar o ID do usuario") Long usuario
) {
}
