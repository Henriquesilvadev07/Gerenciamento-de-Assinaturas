package com.example.Controle_de_Assinaturas.service;

import com.example.Controle_de_Assinaturas.dto.AssinaturasDto;
import com.example.Controle_de_Assinaturas.dto.AssinaturasResponseDto;
import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.model.StatusEnum;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import com.example.Controle_de_Assinaturas.repository.AssinaturasRepository;
import com.example.Controle_de_Assinaturas.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssinaturasService {

    private final AssinaturasRepository assinaturasRepository;
    private final UsersRepository usersRepository;

    private AssinaturasModel verificarEAtualizarStatus(AssinaturasModel assinatura) {
        if (assinatura.getStatus() == StatusEnum.EM_DIA &&
                LocalDate.now().isAfter(assinatura.getProximoVencimento())) {

            assinatura.setStatus(StatusEnum.ATRASADO);
            return assinaturasRepository.save(assinatura);
        }
        return assinatura;
    }

    private LocalDate calcularProximoVencimento(int diaVencimento) {
        LocalDate hoje = LocalDate.now();
        // Garante que o dia é válido para o mês atual
        int diaFinal = Math.min(diaVencimento, hoje.lengthOfMonth());
        LocalDate proximo = hoje.withDayOfMonth(diaFinal);

        // Se a data calculada já passou, joga para o próximo mês
        if (hoje.isAfter(proximo)) {
            proximo = proximo.plusMonths(1).withDayOfMonth(Math.min(diaVencimento, proximo.plusMonths(1).lengthOfMonth()));
        }
        return proximo;
    }

    public AssinaturasModel salvar(AssinaturasDto dto) {
        UsersModel usuarioLogado = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }

        AssinaturasModel assinatura = new AssinaturasModel();
        assinatura.setServico(dto.servico());
        assinatura.setValor(dto.valor());
        assinatura.setDataVencimento(dto.dataVencimento());
        assinatura.setPlano(dto.plano());
        assinatura.setUsuario(usuarioLogado);
        assinatura.setStatus(StatusEnum.EM_DIA);
        assinatura.setProximoVencimento(calcularProximoVencimento(dto.dataVencimento()));

        return assinaturasRepository.save(assinatura);
    }

    public AssinaturasResponseDto acharPorId(Long id) {
        var assinatura = assinaturasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return new AssinaturasResponseDto(verificarEAtualizarStatus(assinatura));
    }

    public List<AssinaturasResponseDto> listarAssinaturas() {
        UsersModel usuarioLogado = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return assinaturasRepository.findByUsuario(usuarioLogado)
                .stream()
                .map(this::verificarEAtualizarStatus)
                .map(AssinaturasResponseDto::new)
                .toList();
    }

    public AssinaturasResponseDto atualizar(Long id, AssinaturasDto dto) {
        var assinatura = assinaturasRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id nao encontrado, Tente um ID valido")
        );
        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31 ) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }

        assinatura.setServico(dto.servico());
        assinatura.setValor(dto.valor());
        assinatura.setDataVencimento(dto.dataVencimento());
        assinatura.setPlano(dto.plano());

        // Recalcula o vencimento caso o usuário mude o dia
        assinatura.setProximoVencimento(calcularProximoVencimento(dto.dataVencimento()));

        // Se o usuário editar a assinatura, supomos que ele está regularizando, então volta para EM_DIA
        assinatura.setStatus(StatusEnum.EM_DIA);

        var assinaturaAtualizada = assinaturasRepository.save(assinatura);
        return new AssinaturasResponseDto(assinaturaAtualizada);
    }

    public void deletar(Long id){
        if (assinaturasRepository.existsById(id)) {
            assinaturasRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Id nao encontrado, Tente um ID valido");
        }
    }
}