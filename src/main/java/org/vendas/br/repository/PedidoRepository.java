package org.vendas.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vendas.br.model.Cliente;
import org.vendas.br.model.Pedido;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findPedidoByClienteId(Integer id);
}
