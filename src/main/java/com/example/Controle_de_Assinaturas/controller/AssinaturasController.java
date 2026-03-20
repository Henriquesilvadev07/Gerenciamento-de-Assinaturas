package com.example.Controle_de_Assinaturas.controller;

import com.example.Controle_de_Assinaturas.dto.AssinaturasDto;
import com.example.Controle_de_Assinaturas.model.Entity;
import com.example.Controle_de_Assinaturas.service.AssinaturasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssinaturasController {

    private final AssinaturasService assinaturasService;

    public AssinaturasController(AssinaturasService assinaturasService) {
        this.assinaturasService = assinaturasService;
    }

    @PostMapping
    public ResponseEntity<Entity> salvar(@RequestBody AssinaturasDto dto) {
        Entity novaAssinatura = assinaturasService.salvar(dto);
        return ResponseEntity.ok(novaAssinatura);
    }

    @GetMapping
    public ResponseEntity<List<Entity>> listar() {
        return ResponseEntity.ok(assinaturasService.listarAssinaturas());
    }

    @PutMapping
    public ResponseEntity<Entity> atualizar(@RequestBody AssinaturasDto dto) {
        Entity assinaturaAtualizada = assinaturasService.salvar(dto);
        return ResponseEntity.ok(assinaturaAtualizada);
    }

    @DeleteMapping
    public void deletar(@PathVariable long id) {
        assinaturasService.deletar(id);
    }
}
