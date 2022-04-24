package org.vendas.br.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotEmpty(message = "{campo.login-usuario}")
    private String login;

    @NotEmpty(message = "{campo.senha-usuario}")
    private String senha;
}
