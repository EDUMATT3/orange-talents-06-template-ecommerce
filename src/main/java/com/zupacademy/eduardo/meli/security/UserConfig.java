package com.zupacademy.eduardo.meli.security;

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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@Component
//public class UserConfig {
//
//    @Resource(name = BeanIds.AUTHENTICATION_MANAGER)
//    private AuthenticationManager authenticationManager;
//
//    @EventListener(ApplicationReadyEvent.class)
//    private void init() throws Exception {
//        Authentication senha = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("usuario1@email.com", "senha"));
//
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(senha);
//    }
//}
