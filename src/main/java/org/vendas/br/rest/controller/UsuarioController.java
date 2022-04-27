package org.vendas.br.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.dto.TokenDTO;
import org.vendas.br.dto.UsuarioDTO;
import org.vendas.br.exceptions.SenhaInvalidException;
import org.vendas.br.model.Usuario;
import org.vendas.br.security.jwt.JwtService;
import org.vendas.br.service.Impl.UserDetailServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UserDetailServiceImpl instanceOfUsuarioService;
    @Autowired
    private JwtService instanceOfJwtService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario salvarUsuario (@RequestBody @Valid UsuarioDTO dto) {
        return instanceOfUsuarioService.salvar(dto);
    }

    @PostMapping("/auth")
    public TokenDTO autenticarUsuario (@RequestBody @Valid UsuarioDTO dto) {
        try {
            UserDetails usuarioLogado = instanceOfUsuarioService.autenticar(dto);
            Usuario usuario = Usuario
                    .builder()
                    .login(usuarioLogado.getUsername())
                    .senha(usuarioLogado.getPassword())
                    .build();

            String token = instanceOfJwtService.getToken(usuario);
            return new TokenDTO(usuario.getLogin(),token);

        } catch (UsernameNotFoundException | SenhaInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
