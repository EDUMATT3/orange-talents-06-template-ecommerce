package com.zupacademy.eduardo.meli.security;

import com.zupacademy.eduardo.meli.usuario.UserService;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.PersistenceContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
//public class UserConfig {
//
//    @Resource(name = BeanIds.AUTHENTICATION_MANAGER)
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    UserService userService;
//
//    @EventListener(ApplicationReadyEvent.class)
//    private void init() throws Exception {
//        Usuario usuario = userService.loadUserById(1L);
//
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
//    }
//}

//public  class UserConfig extends OncePerRequestFilter {
//
//    UserService userService;
//
//    public UserConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        Usuario usuario = userService.loadUserById(1L);
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
//    }
//}