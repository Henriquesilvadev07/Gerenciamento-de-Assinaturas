package com.example.Controle_de_Assinaturas.service;

import com.example.Controle_de_Assinaturas.dto.AssinaturasDto;
import com.example.Controle_de_Assinaturas.model.Entity;
import com.example.Controle_de_Assinaturas.repository.AssinaturasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssinaturasService {

    private final AssinaturasRepository assinaturasRepository;

    public AssinaturasService(AssinaturasRepository assinaturasRepository) {
        this.assinaturasRepository = assinaturasRepository;
    }

    public Entity salvar(AssinaturasDto dto) {
        Entity novaAssinatura = new Entity();
        novaAssinatura.setServico(dto.servico());
        novaAssinatura.setValor(dto.valor());
        novaAssinatura.setDataVencimento(dto.dataVencimento());
        novaAssinatura.setStatus(dto.status());
        novaAssinatura.setPlano(dto.plano());

        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }

        return assinaturasRepository.save(novaAssinatura);
    }

    public List<Entity> listarAssinaturas() {
        return assinaturasRepository.findAll();
    }

    public Entity atualizar(Long id, AssinaturasDto dto) {
        Entity assinaturaAtualizada = new Entity();
        assinaturaAtualizada.setId(id);
        assinaturaAtualizada.setServico(dto.servico());
        assinaturaAtualizada.setValor(dto.valor());
        assinaturaAtualizada.setDataVencimento(dto.dataVencimento());
        assinaturaAtualizada.setStatus(dto.status());
        assinaturaAtualizada.setPlano(dto.plano());

        if (dto.dataVencimento() < 1 || dto.dataVencimento() > 31 ) {
            throw new IllegalArgumentException("Dia de vencimento invalido");
        }

        return assinaturasRepository.save(assinaturaAtualizada);
    }

    public void deletar(Long id){
        assinaturasRepository.deleteById(id);
    }
}