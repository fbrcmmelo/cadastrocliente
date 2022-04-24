package org.vendas.br.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vendas.br.dto.ClienteDTO;
import org.vendas.br.model.Cliente;
import org.vendas.br.repository.ClienteRepository;
import org.vendas.br.service.ClienteService;

import java.util.List;
import java.util.Optional;

@Service @Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository instanceOfClienteRepository;

    @Override
    public Cliente saveCliente (ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        return instanceOfClienteRepository.save(cliente);
    }

    public Cliente updateCliente (Cliente cliente, ClienteDTO dto) {
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        return instanceOfClienteRepository.save(cliente);
    }

    @Override
    public void removeCliente (Cliente cliente) {
        instanceOfClienteRepository.delete(cliente);
    }

    @Override @Transactional(readOnly = true)
    public List<Cliente> findAllClienteWithFilter (Example example) {
        return instanceOfClienteRepository.findAll(example);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Cliente> findClienteById (Integer id) {
        return instanceOfClienteRepository.findById(id);
    }
}
