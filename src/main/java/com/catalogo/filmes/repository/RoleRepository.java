package com.catalogo.filmes.repository;


import com.catalogo.filmes.model.Role;
import com.catalogo.filmes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String username);
}
