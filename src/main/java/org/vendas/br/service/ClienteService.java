package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.dto.ClienteDTO;
import org.vendas.br.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente salvar(ClienteDTO dto);

    Cliente atualizar(Integer id, ClienteDTO dto);

    void remover(Integer id);

    List<Cliente> buscarTodosComFiltro(Example example);

    Optional<Cliente> buscarPorId(Integer id);
}
