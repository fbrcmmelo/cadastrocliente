package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.dto.ClienteDTO;
import org.vendas.br.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente saveCliente (ClienteDTO dto);

    Cliente updateCliente (Cliente cliente, ClienteDTO dto);

    void removeCliente (Cliente cliente);

    List<Cliente> findAllClienteWithFilter (Example example);

    Optional<Cliente> findClienteById (Integer id);
}
