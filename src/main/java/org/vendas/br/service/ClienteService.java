package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente saveCliente (Cliente cliente);

    Cliente updateCliente (Cliente cliente);

    void deleteCliente (Cliente Cliente);

    List<Cliente> findAllClientes (Example example);

    Cliente findClienteById (Integer id);
}
