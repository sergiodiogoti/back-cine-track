package com.catalogo.filmes.security;

import com.catalogo.filmes.model.Usuario;
import com.catalogo.filmes.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenService tokenService;
    private final UsuarioRepository userRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Recupera o token do cabeçalho "Authorization"
        var token = this.recoverToken(request);

        if(token != null) {
            // 2. Valida o token e pega o username
            var login = tokenService.validateToken(token);

            // 3. Busca o usuário no banco para confirmar que ele existe
           Usuario user = userRepository.findByUsername(login)
                   .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            // 4. Se o usuário for válido, "avisa" ao Spring Security que ele está autenticado
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 5. Segue com a requisição para o Controller
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
