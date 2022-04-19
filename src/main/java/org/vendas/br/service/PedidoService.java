package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.dto.PedidoCompletoDTO;
import org.vendas.br.dto.PedidoDTO;
import org.vendas.br.enums.StatusPedido;
import org.vendas.br.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<PedidoCompletoDTO> findByIdFetchItem(Integer id);

    Optional<Pedido> findById(Integer id);

    void updateStatus(Integer id, StatusPedido status) throws Exception;
}

