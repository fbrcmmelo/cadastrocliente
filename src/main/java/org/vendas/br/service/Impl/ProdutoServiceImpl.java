package org.vendas.br.service.Impl;

import org.springframework.data.domain.Example;
import org.vendas.br.model.Produto;
import org.vendas.br.repository.ProdutoRepository;
import org.vendas.br.service.ProdutoService;

import java.util.List;

public class ProdutoServiceImpl implements ProdutoService {

    ProdutoRepository instanceOfProduto;

    @Override
    public Produto saveProduto(Produto produto) {
        return null;
    }

    @Override
    public Produto updateProduto(Produto produto) {
        return null;
    }

    @Override
    public void delete(Produto produto) {

    }

    @Override
    public List<Produto> findAllProdutos(Example example) {
        return null;
    }

    @Override
    public Produto findProdutoById(Integer id) {
        return null;
    }
}
