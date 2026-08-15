package com.example.Controle_de_Assinaturas.controller;


import com.example.Controle_de_Assinaturas.dto.TokenJwtDto;
import com.example.Controle_de_Assinaturas.dto.UsersDto;
import com.example.Controle_de_Assinaturas.model.UsersModel;
import com.example.Controle_de_Assinaturas.service.TokenService;
import com.example.Controle_de_Assinaturas.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    public final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsersDto dto){
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authenticationManager.authenticate(authToken);
        var JwtToken = tokenService.gerarToken((UsersModel) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(JwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<UsersModel> cadastro (@RequestBody @Valid UsersDto dto, UriComponentsBuilder uriBuilder){
        var users = usersService.cadastrar(dto);
        var uri = uriBuilder.path("/auth/registro/{id}").buildAndExpand(users.getId()).toUri();
        return ResponseEntity.created(uri).body(users);
    }

}
