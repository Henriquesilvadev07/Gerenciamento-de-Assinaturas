package com.example.Controle_de_Assinaturas.controller;

import com.example.Controle_de_Assinaturas.dto.UsersDto;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import com.example.Controle_de_Assinaturas.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsersController {

    private final UsersService usersService;


    @PostMapping("/{id}")
    public ResponseEntity<UsersModel> atualizarPorId(@PathVariable Long id, @RequestBody @Valid UsersDto dto){
        var user = usersService.atualizarPorId(id, dto);
        return ResponseEntity.status(200).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        usersService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
