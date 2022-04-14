package org.vendas.br.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.model.Produto;
import org.vendas.br.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository instanceOfProduto;

    public ProdutoController (ProdutoRepository instanceOfProduto) {
        if (this.instanceOfProduto == null) {
            this.instanceOfProduto = instanceOfProduto;
        }
    }

    @GetMapping()
    public List<Produto> findAll () {
    List<Produto> listaProdutos = instanceOfProduto.findAll();
    if (!listaProdutos.isEmpty()) { return listaProdutos;}
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save (@RequestBody Produto produto) {
        instanceOfProduto.save(produto);
        return produto;
    }

    @PutMapping("{id}")
    public Produto update (@RequestBody Produto produtoParameter,
                           @PathVariable("id") Integer idProduto) {
        Optional<Produto> produto = instanceOfProduto.findById(idProduto);
        if (produto.isPresent()) {
            produtoParameter.setId(produto.get().getId());
            instanceOfProduto.save(produtoParameter);
            return produtoParameter;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") Integer idProduto) {
        Optional<Produto> produto = instanceOfProduto.findById(idProduto);
        if (!produto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        instanceOfProduto.delete(produto.get());
    }

    @GetMapping("{id}")
    public Produto findProdutoById (@PathVariable("id") Integer idProduto) {
        Optional<Produto> produto = instanceOfProduto.findById(idProduto);
        if (produto.isPresent()) {
            return produto.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find")
    public List<Produto> findProdutoWithFilter (Produto produtoParameter) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produtoParameter, matcher);
        List<Produto> listaProdutos = instanceOfProduto.findAll(example);
        return listaProdutos;
    }
}
