package org.vendas.br.service;

import org.springframework.data.domain.Example;
import org.vendas.br.model.Produto;

import java.util.List;

public interface ProdutoService {
    Produto saveProduto (Produto produto);

    Produto updateProduto (Produto produto);

    void delete (Produto produto);

    List<Produto> findAllProdutos (Example example);

    Produto findProdutoById (Integer id);
}
