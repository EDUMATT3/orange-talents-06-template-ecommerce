package com.zupacademy.eduardo.meli.security;

import com.zupacademy.eduardo.meli.usuario.UserService;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("dev")
public  class UserConfig extends OncePerRequestFilter {

    UserService userService;

    public UserConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Usuario usuario = userService.loadUserById(1L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}