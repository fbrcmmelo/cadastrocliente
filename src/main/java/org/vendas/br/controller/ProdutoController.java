package org.vendas.br.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.dto.ProdutoDTO;
import org.vendas.br.model.Produto;
import org.vendas.br.service.Impl.ProdutoServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServiceImpl instanceOfProdutoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save (@RequestBody @Valid ProdutoDTO dto) {
        Produto produto = instanceOfProdutoService.saveProduto(dto);
        return produto;
    }

    @PutMapping("{id}")
    public Produto update (@RequestBody @Valid ProdutoDTO dto,
                           @PathVariable("id") Integer id) {
        Optional<Produto> produto = instanceOfProdutoService.findProdutoById(id);
        if (!produto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return instanceOfProdutoService.updateProduto(produto.get(), dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") Integer id) {
        Optional<Produto> produto = instanceOfProdutoService.findProdutoById(id);
        if (!produto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        instanceOfProdutoService.delete(produto.get());
    }

    @GetMapping("{id}")
    public Produto findProdutoById (@PathVariable("id") Integer id) {
        Optional<Produto> produto = instanceOfProdutoService.findProdutoById(id);
        if (!produto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return produto.get();
    }

    @GetMapping()
    public List<Produto> findProdutoWithFilter (Produto produtoParameter) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produtoParameter, matcher);
        List<Produto> listaProdutos = instanceOfProdutoService.findAllProdutos(example);
        return listaProdutos;
    }
}
