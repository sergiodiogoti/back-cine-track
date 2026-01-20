package com.catalogo.filmes.service;

import com.catalogo.filmes.dto.CreateUserRequest;
import com.catalogo.filmes.model.Role;
import com.catalogo.filmes.model.UserRole;
import com.catalogo.filmes.model.Usuario;
import com.catalogo.filmes.repository.RoleRepository;
import com.catalogo.filmes.repository.UserRoleRepository;
import com.catalogo.filmes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }


    public void createUser(CreateUserRequest request) {
       var roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER não encontrada"));
        salvar(request, roleUser);
    }

    public void createAdmin(CreateUserRequest request){
        var roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN não encontrada"));
        salvar(request, roleAdmin);
    }

    @Transactional
    private void salvar(CreateUserRequest request, Role role){
        if (usuarioRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        Usuario usuario = Usuario.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(role))
                .build();
        usuarioRepository.save(usuario);


        UserRole userRole = UserRole.builder()
                .role(role)
                .usuario(usuario)
                .build();
        userRoleRepository.save(userRole);
    }
}
