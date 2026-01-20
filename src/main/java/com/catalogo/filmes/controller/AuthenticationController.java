package com.catalogo.filmes.controller;

import com.catalogo.filmes.dto.Authentication;
import com.catalogo.filmes.dto.CreateUserRequest;
import com.catalogo.filmes.dto.LoginResponse;
import com.catalogo.filmes.model.Usuario;
import com.catalogo.filmes.security.TokenService;
import com.catalogo.filmes.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Authentication data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (Usuario) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new LoginResponse(token, roles));
    }


    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest request) {
        usuarioService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmin(@RequestBody @Valid CreateUserRequest request) {
        usuarioService.createAdmin(request);
    }
}