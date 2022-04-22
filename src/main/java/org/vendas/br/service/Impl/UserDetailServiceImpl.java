package org.vendas.br.service.Impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vendas.br.model.Usuario;
import org.vendas.br.repository.UserRepository;

import java.util.Optional;

// Aqui vamos criar o servico para tratar a autenticacao dos usuarios
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository instanceOfUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = instanceOfUserRepository.findByLogin(username);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado !");
        }

        String[] userRole = usuario.get().isAdmin() ? new String[] {"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.get().getLogin())
                .password(usuario.get().getSenha())
                .roles(userRole)
                .build();
    }
}