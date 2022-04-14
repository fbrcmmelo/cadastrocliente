package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.dto.ItemPedidoDTO;
import org.vendas.br.model.ItemPedido;
import org.vendas.br.model.Pedido;

import java.util.List;

public interface ItemPedidoService {
    List<ItemPedido> salvarTodos(Pedido id, List<ItemPedidoDTO> itemsDTO);

    ItemPedido updateItem (ItemPedido item);

    void delete (ItemPedido item);

    List<ItemPedido> findAllItens (Example example);

    ItemPedido findItemPedidoById (Integer id);
}
