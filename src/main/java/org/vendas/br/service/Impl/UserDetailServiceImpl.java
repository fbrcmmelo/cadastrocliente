package org.vendas.br.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vendas.br.dto.UsuarioDTO;
import org.vendas.br.model.Usuario;
import org.vendas.br.repository.UsuarioRepository;

import java.util.Optional;

// Aqui vamos criar o servico para tratar a autenticacao dos usuarios
@Service @Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsuarioRepository instanceOfUsuarioRepository;

    public Usuario salvar (UsuarioDTO dto) {
        String senhaCriptografada = encoder.encode(dto.getSenha());
        Usuario usuario = new Usuario();
        usuario.setSenha(senhaCriptografada);
        usuario.setLogin(dto.getLogin());
        return instanceOfUsuarioRepository.save(usuario);
    }

    @Override @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = instanceOfUsuarioRepository.findByLogin(username);
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