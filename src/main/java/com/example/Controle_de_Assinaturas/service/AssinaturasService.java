package com.example.Controle_de_Assinaturas.service;

import com.example.Controle_de_Assinaturas.dto.AssinaturasDto;
import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.repository.AssinaturasRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssinaturasService {

    private final AssinaturasRepository assinaturasRepository;

    public AssinaturasModel salvar(AssinaturasDto dto) {
        AssinaturasModel assinatura = new AssinaturasModel();
        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }
        assinatura.setServico(dto.servico());
        assinatura.setValor(dto.valor());
        assinatura.setDataVencimento(dto.dataVencimento());
        assinatura.setStatus(dto.statusEnum());
        assinatura.setPlano(dto.plano());
        return assinaturasRepository.save(assinatura);
    }

    public AssinaturasModel acharPorId(Long id) {
        return assinaturasRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Id nao encontrado, tente algum ID valido"));
    }

    public List<AssinaturasModel> listarAssinaturas() {
        return assinaturasRepository.findAll();
    }

    public AssinaturasModel atualizar(Long id, AssinaturasDto dto) {
        AssinaturasModel assinatura = assinaturasRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id nao encontrado, Tente um ID valido")
        );
        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31 ) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }
        assinatura.setServico(dto.servico());
        assinatura.setValor(dto.valor());
        assinatura.setDataVencimento(dto.dataVencimento());
        assinatura.setStatus(dto.statusEnum());
        assinatura.setPlano(dto.plano());
        return assinaturasRepository.saveAndFlush(assinatura);
    }

    public void deletar(Long id){
        if (assinaturasRepository.existsById(id)) {
            assinaturasRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Id nao encontrado, Tente um ID valido");
        }
    }
}