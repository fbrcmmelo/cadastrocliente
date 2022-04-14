package org.vendas.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vendas.br.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
