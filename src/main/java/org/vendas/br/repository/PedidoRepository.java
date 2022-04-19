package org.vendas.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vendas.br.model.Cliente;
import org.vendas.br.model.ItemPedido;
import org.vendas.br.model.Pedido;

import java.util.Optional;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findPedidoByClienteId(Integer id);
    @Query(value = "Select p from Pedido p left join fetch p.itens where p.id = :id")
    Optional<Pedido> findPedidoFetchItem(Integer id);
}
