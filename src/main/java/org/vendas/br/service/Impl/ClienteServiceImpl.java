package org.vendas.br.service.Impl;

import org.springframework.data.domain.Example;
import org.vendas.br.model.Cliente;
import org.vendas.br.repository.ClienteRepository;
import org.vendas.br.service.ClienteService;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    ClienteRepository instanceOfCliente;

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        return null;
    }

    @Override
    public void deleteCliente(Cliente Cliente) {

    }

    @Override
    public List<Cliente> findAllClientes(Example example) {
        return null;
    }

    @Override
    public Cliente findClienteById(Integer id) {
        return null;
    }
}
