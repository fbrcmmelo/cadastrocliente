package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.dto.PedidoDTO;
import org.vendas.br.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Pedido updatePedido (Pedido pedido);

    void deletePedido (Integer id);

    List<Pedido> findAllPedidos ();

    List<Pedido> findAllPedidosWithFilter(Example example);

    Optional<Pedido> findPedidoById(Integer id);

}
