package org.vendas.br.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vendas.br.dto.ItemCompletoDTO.ItemCompletoDTO;
import org.vendas.br.dto.PedidoCompletoDTO;
import org.vendas.br.dto.PedidoDTO;
import org.vendas.br.enums.StatusPedido;
import org.vendas.br.exceptions.ExceptionsRules;
import org.vendas.br.model.Cliente;
import org.vendas.br.model.ItemPedido;
import org.vendas.br.model.Pedido;
import org.vendas.br.repository.ClienteRepository;
import org.vendas.br.repository.PedidoRepository;
import org.vendas.br.service.ItemPedidoService;
import org.vendas.br.service.PedidoService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository instanceOfPedidoRepository;
    private final ClienteRepository instanceOfClienteRepository;
    private final ItemPedidoService instanceOfItemPedidoService;

    @Override
    public Pedido salvar(PedidoDTO dto) {
        Optional<Cliente> cliente = instanceOfClienteRepository.findById(dto.getCliente());
        if (!cliente.isPresent()) throw new ExceptionsRules("Cliente inválido !");

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente.get());
        pedido.setStatus(StatusPedido.REALIZADO);
        instanceOfPedidoRepository.save(pedido);

        List<ItemPedido> listaItens = instanceOfItemPedidoService.salvarTodos(pedido, dto.getItens());
        pedido.setItens(listaItens);
        return pedido;
    }

    @Override
    public Optional<PedidoCompletoDTO> findByIdFetchItem(Integer id) {
        Optional<Pedido> pedidoCompleto = instanceOfPedidoRepository.findPedidoFetchItem(id);
        if (!pedidoCompleto.isPresent()) return Optional.empty();

        PedidoCompletoDTO pedidoCompletoDTO = converterPedido(pedidoCompleto.get());
        pedidoCompletoDTO.setItens(converterItemPedido(pedidoCompleto.get().getItens()));
        return Optional.of(pedidoCompletoDTO);
        }

    @Override
    public Optional<Pedido> findById(Integer id) {
        return instanceOfPedidoRepository.findById(id);
    }

    @Override
    public void updateStatus(Pedido pedido, StatusPedido status) {
        pedido.setStatus(status);
        instanceOfPedidoRepository.save(pedido);
    }

    private List<ItemCompletoDTO> converterItemPedido(List<ItemPedido> itens) {
        if (itens.isEmpty()) return new ArrayList<>();
        return itens
                .stream()
                .map(eachItem -> {
                    ItemCompletoDTO itemCompleto = new ItemCompletoDTO();
                    itemCompleto.setDescricao(eachItem.getProduto().getDescricao());
                    itemCompleto.setPrecoUnitario(eachItem.getProduto().getPrecoUnitario());
                    itemCompleto.setQuantidade(eachItem.getQuantidade());
                    return itemCompleto;
                }).collect(Collectors.toList());
    }

    private PedidoCompletoDTO converterPedido(Pedido pedidoCompleto) {
        return PedidoCompletoDTO
                .builder()
                .id(pedidoCompleto.getId())
                .nomeCliente(pedidoCompleto.getCliente().getNome())
                .cpfCliente(pedidoCompleto.getCliente().getCpf())
                .data(pedidoCompleto.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .status(pedidoCompleto.getStatus().name())
                .total(pedidoCompleto.getTotal())
                .build();
    }
}

