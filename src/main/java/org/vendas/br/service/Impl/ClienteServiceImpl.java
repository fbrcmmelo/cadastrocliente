package org.vendas.br.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vendas.br.dto.ClienteDTO;
import org.vendas.br.exceptions.ExceptionsRules;
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
    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        return instanceOfClienteRepository.save(cliente);
    }

    public Cliente atualizar(Integer id, ClienteDTO dto) {
        Optional<Cliente> cliente = instanceOfClienteRepository.findById(id);
        if (!cliente.isPresent()) throw new ExceptionsRules("Cliente inválido !");
        cliente.get().setNome(dto.getNome());
        cliente.get().setCpf(dto.getCpf());
        return instanceOfClienteRepository.save(cliente.get());
    }

    @Override
    public void remover(Integer id) {
        Optional<Cliente> cliente = instanceOfClienteRepository.findById(id);
        instanceOfClienteRepository.delete(cliente.get());
    }

    @Override @Transactional(readOnly = true)
    public List<Cliente> buscarTodosComFiltro(Example example) {
        return instanceOfClienteRepository.findAll(example);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorId(Integer id) {
        return instanceOfClienteRepository.findById(id);
    }
}
