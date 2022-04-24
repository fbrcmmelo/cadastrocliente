package org.vendas.br.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vendas.br.dto.ProdutoDTO;
import org.vendas.br.model.Produto;
import org.vendas.br.repository.ProdutoRepository;
import org.vendas.br.service.ProdutoService;

import java.util.List;
import java.util.Optional;

@Service @Transactional
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository instanceOfProdutoRepository;

    @Override
    public Produto saveProduto (ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        return instanceOfProdutoRepository.save(produto);
    }

    @Override
    public Produto updateProduto  (Produto produto,ProdutoDTO dto) {
        produto.setDescricao(dto.getDescricao());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        instanceOfProdutoRepository.save(produto);
        return produto;
    }

    @Override
    public void delete (Produto produto) {
        instanceOfProdutoRepository.delete(produto);
    }

    @Override @Transactional(readOnly = true)
    public List<Produto> findAllProdutos (Example example) {
        return instanceOfProdutoRepository.findAll(example);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Produto> findProdutoById (Integer id) {return instanceOfProdutoRepository.findById(id);}
}
