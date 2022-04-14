package org.vendas.br.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.vendas.br.dto.PedidoDTO;
import org.vendas.br.exceptions.ExceptionsRules;
import org.vendas.br.model.Cliente;
import org.vendas.br.model.ItemPedido;
import org.vendas.br.model.Pedido;
import org.vendas.br.repository.ClienteRepository;
import org.vendas.br.repository.ItemPedidoRepository;
import org.vendas.br.repository.PedidoRepository;
import org.vendas.br.repository.ProdutoRepository;
import org.vendas.br.service.ItemPedidoService;
import org.vendas.br.service.PedidoService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service @Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository instanceOfPedidoRepository;
    private final ClienteRepository instanceOfClienteRepository;
    private final ProdutoRepository instanceOfProdutoRepository;
    private final ItemPedidoService instanceOfItemPedidoService;

    @Override
    public Pedido salvar(PedidoDTO dto) {
        Optional<Cliente> cliente = instanceOfClienteRepository.findById(dto.getCliente());
        if (!cliente.isPresent()) throw new ExceptionsRules("Cliente inválido !");

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente.get());
        instanceOfPedidoRepository.save(pedido);

        List<ItemPedido> listaItens = instanceOfItemPedidoService.salvarTodos(pedido, dto.getItens());
        pedido.setItens(listaItens);
        return pedido;
    }

    @Override
    public Pedido updatePedido(Pedido pedido) {
        return instanceOfPedidoRepository.save(pedido);
    }

    @Override
    public void deletePedido(Integer id) {
        Optional<Pedido> pedido = instanceOfPedidoRepository.findById(id);
        if (pedido.isPresent()){
            instanceOfPedidoRepository.delete(pedido.get());
        }
    }

    @Override
    public List<Pedido> findAllPedidos() {
       return instanceOfPedidoRepository.findAll();
    }

    @Override
    public List<Pedido> findAllPedidosWithFilter(Example example) {
        return instanceOfPedidoRepository.findAll(example);
    }

    @Override
    public Optional<Pedido> findPedidoById(Integer id) {
        Optional<Pedido> pedido = instanceOfPedidoRepository.findById(id);
        if (pedido.isPresent()) {
            return pedido;
        }
        return null;
    }
}
