package org.vendas.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vendas.br.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}

