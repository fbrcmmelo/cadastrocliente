package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.dto.ProdutoDTO;
import org.vendas.br.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto saveProduto (ProdutoDTO dto);

    Produto updateProduto (Produto produto, ProdutoDTO dto);

    void delete (Produto produto);

    List<Produto> findAllProdutos (Example example);

    Optional<Produto> findProdutoById (Integer id);
}
