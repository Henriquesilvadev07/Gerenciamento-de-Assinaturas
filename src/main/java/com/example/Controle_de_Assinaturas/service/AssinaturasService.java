package com.example.Controle_de_Assinaturas.service;

import com.example.Controle_de_Assinaturas.dto.AssinaturasDto;
import com.example.Controle_de_Assinaturas.dto.AssinaturasResponseDto;
import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import com.example.Controle_de_Assinaturas.repository.AssinaturasRepository;
import com.example.Controle_de_Assinaturas.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssinaturasService {

    private final AssinaturasRepository assinaturasRepository;

    private final UsersRepository usersRepository;

    public AssinaturasModel salvar(AssinaturasDto dto) {
        // 1. Obtenha o usuário autenticado de forma segura pelo contexto de segurança
        UsersModel usuarioLogado = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }
        // 3. Mapeie os dados, mas SEMPRE use o usuarioLogado, ignorando o dto.usuario()
        AssinaturasModel assinatura = new AssinaturasModel();
        assinatura.setServico(dto.servico());
        assinatura.setValor(dto.valor());
        assinatura.setDataVencimento(dto.dataVencimento());
        assinatura.setStatus(dto.status());
        assinatura.setPlano(dto.plano());
        assinatura.setUsuario(usuarioLogado); // <-- AQUI A MÁGICA: Vincula ao dono do Token

        return assinaturasRepository.save(assinatura);
    }

    public AssinaturasResponseDto acharPorId(Long id) {
        var assinaturas = assinaturasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
        return new AssinaturasResponseDto(assinaturas);
    }

    public List<AssinaturasResponseDto> listarAssinaturas() {
        UsersModel usuarioLogado = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return assinaturasRepository.findByUsuario(usuarioLogado)
                .stream()
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
        assinatura.setStatus(dto.status());
        assinatura.setPlano(dto.plano());
        var assinaturaAtualizada = assinaturasRepository.save(assinatura);
        return new AssinaturasResponseDto(assinaturaAtualizada);
    }

    public void deletar(Long id){
        if (assinaturasRepository.existsById(id)) {
            assinaturasRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Id nao encontrado, Tente um ID valido");
        }
    }
}