package com.catalogo.filmes.repository;

import com.catalogo.filmes.model.UserRole;
import com.catalogo.filmes.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findByUsuarioUsername(String username);

    List<UserRole> findByUsuarioId(Long userId);

}