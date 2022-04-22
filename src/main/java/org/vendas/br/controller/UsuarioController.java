package org.vendas.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vendas.br.model.Usuario;
import org.vendas.br.service.UsuarioService;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService instanceOfUsuarioService;

    public Usuario salvarUsuario (@RequestBody @Valid Usuario usuario) {
        return instanceOfUsuarioService.saveAll(usuario);
    }
}
