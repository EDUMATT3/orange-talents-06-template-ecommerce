package com.zupacademy.eduardo.meli.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    EntityManager em;

    @Value("${security.username-query}")
    private String query;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = em.createQuery(query, Usuario.class).setParameter("username", s).getSingleResult();
        return verifyUser(usuario);
    }

    public Usuario loadUserById(Long id){
        Usuario usuario = em.find(Usuario.class, id);

        return verifyUser(usuario);
    }

    private Usuario verifyUser(Usuario usuario){
        Optional<Usuario> possivelUsuario = Optional.ofNullable(usuario);

        if(possivelUsuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return possivelUsuario.get();
    }
}
