package com.zupacademy.eduardo.meli.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zupacademy.eduardo.meli.cliente.AutenticacaoRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroAutenticacaoUsuario extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public FiltroAutenticacaoUsuario(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            AutenticacaoRequest autenticacaoRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), AutenticacaoRequest.class);

            Authentication authentication = autenticacaoRequest.build();

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = tokenService.gerarToken(authResult);

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token);
    }
}