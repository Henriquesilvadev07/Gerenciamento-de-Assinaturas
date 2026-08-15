package com.example.Controle_de_Assinaturas.controller;

import com.example.Controle_de_Assinaturas.dto.AssinaturasDto;
import com.example.Controle_de_Assinaturas.dto.AssinaturasResponseDto;
import com.example.Controle_de_Assinaturas.model.AssinaturasModel;
import com.example.Controle_de_Assinaturas.service.AssinaturasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AssinaturasController {

    private final AssinaturasService assinaturasService;


    @PostMapping
    public ResponseEntity<AssinaturasModel> salvar(@RequestBody @Valid AssinaturasDto dto, UriComponentsBuilder uriBuilder) {
        var assinaturas = assinaturasService.salvar(dto);
        var uri = uriBuilder.path("/assinaturas/{id}").buildAndExpand(assinaturas.getId()).toUri();
        return ResponseEntity.created(uri).body(assinaturas);
    }

    @GetMapping
    public ResponseEntity<List<AssinaturasResponseDto>> listar() {
        var assinaturas = assinaturasService.listarAssinaturas();
        return ResponseEntity.status(200).body(assinaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturasResponseDto> acharPorId(@PathVariable Long id) {
        var assinaturas = assinaturasService.acharPorId(id);
        return ResponseEntity.status(200).body(assinaturas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssinaturasResponseDto> atualizar(@PathVariable Long id, @RequestBody @Valid AssinaturasDto dto) {
        var assinaturas = assinaturasService.atualizar(id, dto);
        return ResponseEntity.status(200).body(assinaturas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        assinaturasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}