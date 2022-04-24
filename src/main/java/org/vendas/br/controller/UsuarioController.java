package org.vendas.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vendas.br.dto.UsuarioDTO;
import org.vendas.br.model.Usuario;
import org.vendas.br.service.Impl.UserDetailServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UserDetailServiceImpl instanceOfUsuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario salvarUsuario (@RequestBody @Valid UsuarioDTO dto) {
        return instanceOfUsuarioService.salvar(dto);
    }
}
