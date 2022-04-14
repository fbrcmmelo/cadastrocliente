package org.vendas.br.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vendas.br.dto.PedidoDTO;
import org.vendas.br.model.Pedido;
import org.vendas.br.service.PedidoService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService instanceOfPedidoService;

    @GetMapping()
    public List<Pedido> findAll () {
        List<Pedido> listaPedidos = instanceOfPedidoService.findAllPedidos();
        if (!listaPedidos.isEmpty()) { return listaPedidos;}
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody PedidoDTO dto) {
        Pedido pedido = instanceOfPedidoService.salvar(dto);
        return pedido.getId();
    }
//
//    @PutMapping("{id}")
//    public Pedido update (@RequestBody Pedido pedidoParameter,
//                           @PathVariable("id") Integer idPedido) {
//        Optional<Pedido> pedido = instanceOfPedidoService.findPedidoById(idPedido);
//        if (pedido.isPresent()) {
//            pedidoParameter.setId(pedido.get().getId());
//            instanceOfPedidoService.savePedido(pedidoParameter);
//            return pedidoParameter;
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete (@PathVariable("id") Integer idPedido) {
//        Optional<Pedido> pedido = instanceOfPedidoService.findPedidoById(idPedido);
//        if (!pedido.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        instanceOfPedidoService.deletePedido(pedido.get().getId());
//    }
//
//    @GetMapping("{id}")
//    public Pedido findPedidoById (@PathVariable("id") Integer idPedido) {
//        Optional<Pedido> pedido = instanceOfPedidoService.findPedidoById(idPedido);
//        if (pedido.isPresent()) {
//            return pedido.get();
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/find")
//    public List<Pedido> findPedidoWithFilter (Pedido pedidoParameter) {
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//
//        Example example = Example.of(pedidoParameter, matcher);
//        List<Pedido> listaPedidos = instanceOfPedidoService.findAllPedidosWithFilter(example);
//        return listaPedidos;
//    }
}
